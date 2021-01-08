package pers.dyx.java.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author: dyx
 * @date: 2018/12/3 15:12
 * @description: 时间监听器
 */
public class PaymentStatusUpdateListener implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.printf("支付状态变更. eventName : %s, oldValue : %s, newValue : %s", evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    }
}
