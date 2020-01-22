package com.pentabell.baobabnews.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.io.Serializable;

@Embeddable
public class UserInternauteID implements Serializable {

    @Transient
    private Long UserId;

    @Transient
    private Long InternauteId;

    @OneToOne
    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }
    @OneToOne
    public Long getInternauteId() {
        return InternauteId;
    }

    public void setInternauteId(Long internauteId) {
        InternauteId = internauteId;
    }

    public UserInternauteID() {
    }
    public UserInternauteID(Long InternauteID,Long UserId){
        this.UserId=UserId;
        this.InternauteId=InternauteID;
    }
}
