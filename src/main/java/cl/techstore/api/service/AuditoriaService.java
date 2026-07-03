package cl.techstore.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import tools.jackson.databind.ObjectMapper;

@Service
public class AuditoriaService {

    @Autowired
    private SqsClient sqsClient;

    private final String QUEUE_NAME = "techstore-audit-queue";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Async
    public void publicarAuditoria(String accion, Long productoId, String nombre, String usuario) {
        try {
            Map<String, Object> evento = new HashMap<>();
            evento.put("accion", accion);
            evento.put("productoId", productoId);
            evento.put("nombre", nombre);
            evento.put("usuario", usuario);
            evento.put("fecha", Instant.now().toString());

            String messageBody = objectMapper.writeValueAsString(evento);
            String queueUrl = getQueueUrl();

            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(messageBody)
                    .build();

            SendMessageResponse result = sqsClient.sendMessage(request);
            System.out.println("Mensaje enviado a SQS. MessageId: " + result.messageId());

        } catch (Exception e) {
            System.err.println("Error al publicar auditoría en SQS: " + e.getMessage());
        }
    }

    private String getQueueUrl() {
        try {
            var response = sqsClient.getQueueUrl(req -> req.queueName(QUEUE_NAME));
            return response.queueUrl();
        } catch (Exception e) {
            throw new RuntimeException("Cola SQS no encontrada: " + QUEUE_NAME);
        }
    }
}