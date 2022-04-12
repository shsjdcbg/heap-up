package org.archive.jmx;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.management.Attribute;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanFeatureInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;


public class Client {
    private static final Logger logger = Logger.getLogger(Client.class.getName());


    private static final String USAGE = "Usage: java -jar cmdline-jmxclient.jar USER:PASS HOST:PORT [BEAN] [COMMAND]\nOptions:\n USER:PASS Username and password. Required. If none, pass '-'.\n           E.g. 'controlRole:secret'\n HOST:PORT Hostname and port to connect to. Required. E.g. localhost:8081.\n           Lists registered beans if only USER:PASS and this argument.\n BEANNAME  Optional target bean name. If present we list available operations\n           and attributes.\n COMMAND   Optional operation to run or attribute to fetch. If none supplied,\n           all operations and attributes are listed. Attributes begin with a\n           capital letter: e.g. 'Status' or 'Started'. Operations do not.\n           Operations can take arguments by adding an '=' followed by\n           comma-delimited params. Pass multiple attributes/operations to run\n           more than one per invocation.\nRequirements:\n JDK1.5.0. If connecting to a SUN 1.5.0 JDK JMX Agent, remote side must be\n started with system properties such as the following:\n     -Dcom.sun.management.jmxremote.port=PORT\n     -Dcom.sun.management.jmxremote.authenticate=false\n     -Dcom.sun.management.jmxremote.ssl=false\n The above will start the remote server with no password. See\n http://java.sun.com/j2se/1.5.0/docs/guide/management/agent.html for more on\n 'Monitoring and Management via JMX'.\nClient Use Examples:\n To list MBeans on a non-password protected remote agent:\n     % java -jar cmdline-jmxclient-X.X.jar - localhost:8081 \\\n         org.archive.crawler:name=Heritrix,type=Service\n To list attributes and attributes of the Heritrix MBean:\n     % java -jar cmdline-jmxclient-X.X.jar - localhost:8081 \\\n         org.archive.crawler:name=Heritrix,type=Service \\\n         schedule=http://www.archive.org\n To set set logging level to FINE on a password protected JVM:\n     % java -jar cmdline-jmxclient-X.X.jar controlRole:secret localhost:8081 \\\n         java.util.logging:type=Logging \\\n         setLoggerLevel=org.archive.crawler.Heritrix,FINE";


    protected static final Pattern CMD_LINE_ARGS_PATTERN = Pattern.compile("^([^=]+)(?:(?:\\=)(.+))?$");


    public static void main(String[] args) throws Exception {
        Client client = new Client();

        Logger l = Logger.getLogger("");
        Handler[] hs = l.getHandlers();
        for (int i = 0; i < hs.length; i++) {
            Handler h = hs[0];
            if (h instanceof java.util.logging.ConsoleHandler) {
                client.getClass();
                h.setFormatter(new OneLineSimpleLogger(client));
            }
        }
        args = new String[4];
        args[1] = "192.168.2.176:12345";
        args[2] = "java.lang:type=Memory";
        args[3] = "HeapMemoryUsage";
        client.execute(args);
    }


    protected static void usage() {
        usage(0, null);
    }


    protected static void usage(int exitCode, String message) {
        if (message != null && message.length() > 0) {
            System.out.println(message);
        }
        System.out.println("Usage: java -jar cmdline-jmxclient.jar USER:PASS HOST:PORT [BEAN] [COMMAND]\nOptions:\n USER:PASS Username and password. Required. If none, pass '-'.\n           E.g. 'controlRole:secret'\n HOST:PORT Hostname and port to connect to. Required. E.g. localhost:8081.\n           Lists registered beans if only USER:PASS and this argument.\n BEANNAME  Optional target bean name. If present we list available operations\n           and attributes.\n COMMAND   Optional operation to run or attribute to fetch. If none supplied,\n           all operations and attributes are listed. Attributes begin with a\n           capital letter: e.g. 'Status' or 'Started'. Operations do not.\n           Operations can take arguments by adding an '=' followed by\n           comma-delimited params. Pass multiple attributes/operations to run\n           more than one per invocation.\nRequirements:\n JDK1.5.0. If connecting to a SUN 1.5.0 JDK JMX Agent, remote side must be\n started with system properties such as the following:\n     -Dcom.sun.management.jmxremote.port=PORT\n     -Dcom.sun.management.jmxremote.authenticate=false\n     -Dcom.sun.management.jmxremote.ssl=false\n The above will start the remote server with no password. See\n http://java.sun.com/j2se/1.5.0/docs/guide/management/agent.html for more on\n 'Monitoring and Management via JMX'.\nClient Use Examples:\n To list MBeans on a non-password protected remote agent:\n     % java -jar cmdline-jmxclient-X.X.jar - localhost:8081 \\\n         org.archive.crawler:name=Heritrix,type=Service\n To list attributes and attributes of the Heritrix MBean:\n     % java -jar cmdline-jmxclient-X.X.jar - localhost:8081 \\\n         org.archive.crawler:name=Heritrix,type=Service \\\n         schedule=http://www.archive.org\n To set set logging level to FINE on a password protected JVM:\n     % java -jar cmdline-jmxclient-X.X.jar controlRole:secret localhost:8081 \\\n         java.util.logging:type=Logging \\\n         setLoggerLevel=org.archive.crawler.Heritrix,FINE");
        System.exit(exitCode);
    }


    protected Map formatCredentials(String userpass) {
        Map env = null;
        if (userpass == null || userpass.equals("-")) {
            return env;
        }
        int index = userpass.indexOf(':');
        if (index <= 0) {
            throw new RuntimeException("Unable to parse: " + userpass);
        }
        String[] creds = {userpass.substring(0, index), userpass.substring(index + 1)};

        env = new HashMap(1);
        env.put("jmx.remote.credentials", creds);
        return env;
    }


    protected void execute(String[] args) throws Exception {
        if (args.length == 0 || args.length == 1) {
            usage();
        }
        String userpass = args[0];
        String hostport = args[1];
        String beanName = null;
        String[] command = null;
        if (args.length > 2) {
            beanName = args[2];
        }
        if (args.length > 3) {
            command = new String[args.length - 3];
            for (int i = 3; i < args.length; i++) {
                command[i - 3] = args[i];
            }
        }


        String hostname = hostport;
        int index = hostport.indexOf(':');
        if (index > 0) {
            hostname = hostname.substring(0, index);
        }
        JMXServiceURL rmiurl = new JMXServiceURL("service:jmx:rmi://" + hostport + "/jndi/rmi://" + hostport + "/jmxrmi");

        JMXConnector jmxc = JMXConnectorFactory.connect(rmiurl, formatCredentials(userpass));


        try {
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            ObjectName objName = (beanName == null || beanName.length() <= 0) ? null : new ObjectName(beanName);

            Set beans = mbsc.queryMBeans(objName, null);
            if (beans.size() == 0) {

                logger.severe(objName.getCanonicalName() + " is not a registered bean");
            } else if (beans.size() == 1) {

                ObjectInstance instance = (ObjectInstance) beans.iterator().next();

                doBean(mbsc, instance, command);
            } else {

                for (Iterator i = beans.iterator(); i.hasNext(); ) {
                    Object obj = i.next();
                    if (obj instanceof ObjectName) {
                        System.out.println(((ObjectName) obj).getCanonicalName());
                        continue;
                    }
                    if (obj instanceof ObjectInstance) {
                        System.out.println(((ObjectInstance) obj).getObjectName().getCanonicalName());
                        continue;
                    }
                    logger.severe("Unexpected object type: " + obj);
                }
            }
        } finally {

            jmxc.close();
        }
    }


    protected void doBean(MBeanServerConnection mbsc, ObjectInstance instance, String[] command) throws Exception {
        if (command == null || command.length <= 0) {
            listOptions(mbsc, instance);

            return;
        }

        for (int i = 0; i < command.length; i++) {
            doSubCommand(mbsc, instance, command[i]);
        }
    }


    protected void doSubCommand(MBeanServerConnection mbsc, ObjectInstance instance, String subCommand) throws Exception {
        MBeanAttributeInfo[] attributeInfo = mbsc.getMBeanInfo(instance.getObjectName()).getAttributes();

        MBeanOperationInfo[] operationInfo = mbsc.getMBeanInfo(instance.getObjectName()).getOperations();


        Object result = null;
        if (Character.isUpperCase(subCommand.charAt(0))) {

            if (!isFeatureInfo(attributeInfo, subCommand) && isFeatureInfo(operationInfo, subCommand)) {


                result = doBeanOperation(mbsc, instance, subCommand, operationInfo);

            } else {

                result = doAttributeOperation(mbsc, instance, subCommand, attributeInfo);

            }

        } else if (!isFeatureInfo(operationInfo, subCommand) && isFeatureInfo(attributeInfo, subCommand)) {


            result = doAttributeOperation(mbsc, instance, subCommand, attributeInfo);

        } else {

            result = doBeanOperation(mbsc, instance, subCommand, operationInfo);
        }


        if (result instanceof CompositeData) {
            result = recurseCompositeData(new StringBuffer("\n"), "", "", (CompositeData) result);
        } else if (result instanceof TabularData) {
            result = recurseTabularData(new StringBuffer("\n"), "", "", (TabularData) result);
        } else if (result instanceof String[]) {
            String[] strs = (String[]) result;
            StringBuffer buffer = new StringBuffer("\n");
            for (int i = 0; i < strs.length; i++) {
                buffer.append(strs[i]);
                buffer.append("\n");
            }
            result = buffer;
        }

        if (result != null && logger.isLoggable(Level.INFO)) {
            logger.info(subCommand + ": " + result);
            System.out.println(result);
        }
    }


    protected boolean isFeatureInfo(MBeanFeatureInfo[] infos, String cmd) {
        return (getFeatureInfo(infos, cmd) != null);
    }


    protected MBeanFeatureInfo getFeatureInfo(MBeanFeatureInfo[] infos, String cmd) {
        int index = cmd.indexOf('=');
        String name = (index > 0) ? cmd.substring(0, index) : cmd;
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].getName().equals(name)) {
                return infos[i];
            }
        }
        return null;
    }


    protected StringBuffer recurseTabularData(StringBuffer buffer, String indent, String name, TabularData data) {
        addNameToBuffer(buffer, indent, name);
        Collection c = data.values();
        for (Iterator i = c.iterator(); i.hasNext(); ) {
            Object obj = i.next();
            if (obj instanceof CompositeData) {
                recurseCompositeData(buffer, indent + " ", "", (CompositeData) obj);
                continue;
            }
            if (obj instanceof TabularData) {
                recurseTabularData(buffer, indent, "", (TabularData) obj);
                continue;
            }
            buffer.append(obj);
        }

        return buffer;
    }


    protected StringBuffer recurseCompositeData(StringBuffer buffer, String indent, String name, CompositeData data) {
        indent = addNameToBuffer(buffer, indent, name);
        Iterator i = data.getCompositeType().keySet().iterator();
        while (i.hasNext()) {
            String key = (String) i.next();
            Object o = data.get(key);
            if (o instanceof CompositeData) {
                recurseCompositeData(buffer, indent + " ", key, (CompositeData) o);
                continue;
            }
            if (o instanceof TabularData) {
                recurseTabularData(buffer, indent, key, (TabularData) o);
                continue;
            }
            buffer.append(indent);
            buffer.append(key);
            buffer.append(": ");
            buffer.append(o);
            buffer.append("\n");
        }

        return buffer;
    }


    protected String addNameToBuffer(StringBuffer buffer, String indent, String name) {
        if (name == null || name.length() == 0) {
            return indent;
        }
        buffer.append(indent);
        buffer.append(name);
        buffer.append(":\n");

        return indent + " ";
    }


    protected class CommandParse {
        private String cmd;


        private String[] args;


        protected CommandParse(String command) throws ParseException {
            parse(command);
        }


        private void parse(String command) throws ParseException {
            Matcher m = Client.CMD_LINE_ARGS_PATTERN.matcher(command);
            if (m == null || !m.matches()) {
                throw new ParseException("Failed parse of " + command, 0);
            }

            this.cmd = m.group(1);
            if (m.group(2) != null && m.group(2).length() > 0) {
                this.args = m.group(2).split(",");
            } else {
                this.args = null;
            }
        }


        protected String getCmd() {
            return this.cmd;
        }


        protected String[] getArgs() {
            return this.args;
        }
    }


    protected Object doAttributeOperation(MBeanServerConnection mbsc, ObjectInstance instance, String command, MBeanAttributeInfo[] infos) throws Exception {
        CommandParse parse = new CommandParse(command);
        if (parse.getArgs() == null || parse.getArgs().length == 0) {
            return mbsc.getAttribute(instance.getObjectName(), parse.getCmd());
        }
        if (parse.getArgs().length != 1) {
            throw new IllegalArgumentException("One only argument setting attribute values: " + parse.getArgs());
        }


        MBeanAttributeInfo info = (MBeanAttributeInfo) getFeatureInfo(infos, parse.getCmd());

        Constructor c = Class.forName(info.getType()).getConstructor(new Class[]{String.class});

        Attribute a = new Attribute(parse.getCmd(), c.newInstance(new Object[]{parse.getArgs()[0]}));

        mbsc.setAttribute(instance.getObjectName(), a);
        return null;
    }


    protected Object doBeanOperation(MBeanServerConnection mbsc, ObjectInstance instance, String command, MBeanOperationInfo[] infos) throws Exception {
        CommandParse parse = new CommandParse(command);


        MBeanOperationInfo op = (MBeanOperationInfo) getFeatureInfo(infos, parse.getCmd());

        Object result = null;
        if (op == null) {
            result = "Operation " + parse.getCmd() + " not found.";
        } else {
            MBeanParameterInfo[] paraminfos = op.getSignature();
            int paraminfosLength = (paraminfos == null) ? 0 : paraminfos.length;
            int objsLength = (parse.getArgs() == null) ? 0 : parse.getArgs().length;

            if (paraminfosLength != objsLength) {
                result = "Passed param count does not match signature count";
            } else {
                String[] signature = new String[paraminfosLength];
                Object[] params = (paraminfosLength == 0) ? null : new Object[paraminfosLength];

                for (int i = 0; i < paraminfosLength; i++) {
                    MBeanParameterInfo paraminfo = paraminfos[i];
                    Constructor c = Class.forName(paraminfo.getType()).getConstructor(new Class[]{String.class});


                    params[i] = c.newInstance(new Object[]{parse.getArgs()[i]});

                    signature[i] = paraminfo.getType();
                }
                result = mbsc.invoke(instance.getObjectName(), parse.getCmd(), params, signature);
            }
        }

        return result;
    }


    protected void listOptions(MBeanServerConnection mbsc, ObjectInstance instance) throws InstanceNotFoundException, IntrospectionException, ReflectionException, IOException {
        MBeanInfo info = mbsc.getMBeanInfo(instance.getObjectName());
        MBeanAttributeInfo[] attributes = info.getAttributes();
        if (attributes.length > 0) {
            System.out.println("Attributes:");
            for (int i = 0; i < attributes.length; i++) {
                System.out.println(' ' + attributes[i].getName() + ": " + attributes[i].getDescription() + " (type=" + attributes[i].getType() + ")");
            }
        }


        MBeanOperationInfo[] operations = info.getOperations();
        if (operations.length > 0) {
            System.out.println("Operations:");
            for (int i = 0; i < operations.length; i++) {
                MBeanParameterInfo[] params = operations[i].getSignature();
                StringBuffer paramsStrBuffer = new StringBuffer();
                if (params != null) {
                    for (int j = 0; j < params.length; j++) {
                        paramsStrBuffer.append("\n   name=");
                        paramsStrBuffer.append(params[j].getName());
                        paramsStrBuffer.append(" type=");
                        paramsStrBuffer.append(params[j].getType());
                        paramsStrBuffer.append(" ");
                        paramsStrBuffer.append(params[j].getDescription());
                    }
                }
                System.out.println(' ' + operations[i].getName() + ": " + operations[i].getDescription() + "\n  Parameters " + params.length + ", return type=" + operations[i].getReturnType() + paramsStrBuffer.toString());
            }
        }
    }


    private static class OneLineSimpleLogger
            extends SimpleFormatter {
        private Date date = new Date();


        private FieldPosition position = new FieldPosition(0);


        private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");


        private StringBuffer buffer = new StringBuffer();

        public OneLineSimpleLogger(Client client) {
        }


        @Override
        public String format(LogRecord record) {
            this.buffer.setLength(0);
            this.date.setTime(record.getMillis());
            this.position.setBeginIndex(0);
            this.formatter.format(this.date, this.buffer, this.position);
            this.buffer.append(' ');
            if (record.getSourceClassName() != null) {
                this.buffer.append(record.getSourceClassName());
            } else {
                this.buffer.append(record.getLoggerName());
            }
            this.buffer.append(' ');
            this.buffer.append(formatMessage(record));
            this.buffer.append(System.getProperty("line.separator"));
            if (record.getThrown() != null) {
                try {
                    StringWriter writer = new StringWriter();
                    PrintWriter printer = new PrintWriter(writer);
                    record.getThrown().printStackTrace(printer);
                    writer.close();
                    this.buffer.append(writer.toString());
                } catch (Exception e) {
                    this.buffer.append("Failed to get stack trace: " + e.getMessage());
                }
            }

            return this.buffer.toString();
        }
    }
}
