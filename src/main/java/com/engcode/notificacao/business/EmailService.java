package com.engcode.notificacao.business;

import com.engcode.notificacao.business.dto.TarefasDTO;
import com.engcode.notificacao.infrastructure.exceptions.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {

    //insere dependencia do springboot java mail sender para enviar o email
    private final JavaMailSender javaMailSender;
    //insere a dependencia do Thymeleaf (Serve para aditar o email que vai ser enviado com html)
    private final TemplateEngine templateEngine;

    //Remetente
    // para enviar o e-mail o cara que envia é estático, mas para fim de manutenção vai ser declarado como variável no application properties.
    @Value("${envio.email.remetente}")
    public String remetente;
    //isso é uma identificação do remetente
    @Value("${envio.email.nomeRemetente}")
    private String nomeRemetente;

    //Metodo para enviar o email.
    public void enviaEmail (TarefasDTO tarefasDTO) {
        try {
            //Cria o objeto para enviar o e-mail.
            MimeMessage mensagem = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mensagem, true, StandardCharsets.UTF_8.name());

            //Remetente
            mimeMessageHelper.setFrom(new InternetAddress(remetente, nomeRemetente));
            //Destinatário
            mimeMessageHelper.setTo(InternetAddress.parse(tarefasDTO.getEmailUsuario()));
            //Assunto do e-mail
            mimeMessageHelper.setSubject("Notificação de tarefa.");
            //Começa a criação do contexto do template do e-mail.
            Context context = new Context();
            //seta as variáveis
            context.setVariable("nomeTarefa", tarefasDTO.getNomeTarefa());
            context.setVariable("dataEvento", tarefasDTO.getDataEvento());
            context.setVariable("descricao", tarefasDTO.getDescricao());
            //Chama o template
            String template = templateEngine.process("notificacao", context);
            //seta no corpo do e-mail
            mimeMessageHelper.setText(template, true);
            //Agora evia o e-mail
            javaMailSender.send(mensagem);

        }catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailException("Erro ao enviar o e-mail. ", e.getCause());
        }

    }


}
