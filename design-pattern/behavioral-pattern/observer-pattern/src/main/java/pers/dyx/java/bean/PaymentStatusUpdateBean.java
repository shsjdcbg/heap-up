package pers.dyx.java.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author: dyx
 * @date: 2018/12/3 15:12
 * @description: 事件源
 */
public class PaymentStatusUpdateBean {

    PropertyChangeSupport paymentlisteners = new PropertyChangeSupport(this);

    public void updateStatus(int newStatus) {
        // 模拟业务逻辑
        System.out.println("支付状态更新： " + newStatus);
        // 触发通知
        paymentlisteners.firePropertyChange("paymentStatuUpdate", 0, newStatus);        ;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        paymentlisteners.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        paymentlisteners.removePropertyChangeListener(listener);
    }

}
