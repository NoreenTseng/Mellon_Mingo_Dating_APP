package com.sda.datingapp.dto;

import com.sda.datingapp.model.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    @NotNull(message = "type is null")
    private NotificationType type;

    @NotBlank(message = "content is blank")
    private String content;

    @NotBlank(message = "receiverId is blank")
    private String receiverId;

    @NotBlank(message = "senderId is blank")
    private String senderId;
}

