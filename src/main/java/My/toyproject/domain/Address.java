package My.toyproject.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String zipCode; //우편번호
    private String street;  //도로명 주소
    private String detail;  //상세주소

    protected Address() {
    }

    public Address(String zipCode, String street, String detail) {
        this.zipCode = zipCode;
        this.street = street;
        this.detail = detail;
    }
}
