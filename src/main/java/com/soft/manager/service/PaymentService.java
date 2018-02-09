package com.soft.manager.service;

import com.soft.parent.manager.dao.PaymentMapper;
import com.soft.parent.manager.po.Payment;
import com.soft.parent.manager.po.PaymentKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author jiangmb
 * @Time 2018/2/9.
 */
@Service
public class PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;
    public Payment getPaymentById(Integer id) throws Exception{
        PaymentKey key = new PaymentKey();
        key.setPaymentId(id);
        key.setIsgeneral((byte)1);
        Payment payment = paymentMapper.selectByPrimaryKey(key);
        return payment;
    }
}
