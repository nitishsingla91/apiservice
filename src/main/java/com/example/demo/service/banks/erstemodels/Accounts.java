package com.example.demo.service.banks.erstemodels;

public class Accounts {


private String id;

private Identification identification;

private String currency;

private Servicer servicer;

private String nameI18N;

private String productI18N;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public Identification getIdentification() {
return identification;
}

public void setIdentification(Identification identification) {
this.identification = identification;
}

public String getCurrency() {
return currency;
}

public void setCurrency(String currency) {
this.currency = currency;
}

public Servicer getServicer() {
return servicer;
}

public void setServicer(Servicer servicer) {
this.servicer = servicer;
}

public String getNameI18N() {
return nameI18N;
}

public void setNameI18N(String nameI18N) {
this.nameI18N = nameI18N;
}

public String getProductI18N() {
return productI18N;
}

public void setProductI18N(String productI18N) {
this.productI18N = productI18N;
}

}
