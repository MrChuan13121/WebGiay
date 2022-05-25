package com.ntc.webgiay.service;


import com.ntc.webgiay.model.Order;
import com.ntc.webgiay.model.OrderDetail;
import com.ntc.webgiay.model.Product;
import com.ntc.webgiay.model.User;
import com.ntc.webgiay.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Parser;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class EmailSenderService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailRepository orderDetailRepository;
    public void  sendSimpleEmail(int id) throws MessagingException, UnsupportedEncodingException {
        Order order = orderService.getById(id);
        User user = userService.getById(order.getUser().getId());
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        List<OrderDetail> list = orderDetailRepository.findAllByOrderId(id);
        int total = (int)order.getPrice();
        String mailSubject = user.getFullName() + " có một tin nhắn mới";
        String mailContent = "<p> Thông tin chi tiết đơn hàng </p> <h4>Người nhận:</h4><p>"+order.getNameReceiver()+"</p>";
        mailContent += "<h4>Số điện thoại người nhận:</h4><p>"+order.getPhoneNumberReceiver()+"</p>";
        mailContent += "<h4>Địa chỉ nhận hàng:</h4><p>"+order.getAddressReceiver()+"</p>";
        mailContent += "<h4>Ghi chú:</h4><p>"+order.getNote()+"</p>";
        mailContent += "<table><tr><th>Sản phẩm</th>&emsp;<th>Size</th>&emsp;<th>Số lượng</th></tr>";
        for ( var item : list
             ) {
            mailContent += "<tr><td>"+ item.getProduct().getName()+"</td>&emsp;<td>"+ item.getSize()+"</td>&emsp;<td>"+ item.getQuantity()+"</td></tr>";
        }
        mailContent += "</table><p> Tổng tiền cần thanh toán:"+ total +"vnđ</p>";


        helper.setFrom("nguyenvanchuan13012001@gmail.com","CTN Sneaker");
        helper.setTo(user.getEmail());
        helper.setText(mailContent,true);
        helper.setSubject(mailSubject);

        mailSender.send(message);
    }
}
