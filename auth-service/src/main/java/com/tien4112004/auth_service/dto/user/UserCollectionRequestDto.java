package com.tien4112004.auth_service.dto.user;

import lombok.Getter;
import lombok.Setter;
import com.tien4112004.commonlib.request.BaseCollectionRequest;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

@Getter
@Setter
public class UserCollectionRequestDto extends BaseCollectionRequest  {
    private String email;
    private String username;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime updatedAt;
}