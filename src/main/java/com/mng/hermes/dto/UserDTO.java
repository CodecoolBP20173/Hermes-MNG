package com.mng.hermes.dto;

import com.mng.hermes.model.User;

public class UserDTO {

    private String nickName;
    private String introduction;
    private String givenName;
    private String familyName;
    private String pictureUrl;

    public UserDTO(User user) {
        this.nickName = user.getNickName();
        this.introduction = user.getIntroduction();
        this.givenName = user.getGivenName();
        this.familyName = user.getFamilyName();
        this.pictureUrl = user.getPictureUrl();

    }
}
