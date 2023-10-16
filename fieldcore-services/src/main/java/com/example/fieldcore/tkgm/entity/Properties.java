package com.example.fieldcore.tkgm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Properties {
    private String ilceAd;
    private String mevkii;
    private int ilId;
    private String durum;
    private int ilceId;
    private String zeminKmdurum;
    private String parselNo;
    private String mahalleAd;
    private String ozet;
    private String gittigiParselListe;
    private String gittigiParselSebep;
    private String alan;
    private String adaNo;
    private String nitelik;
    private String ilAd;
    private int mahalleId;
    private String pafta;

    public Properties(JSONObject properties) {
        this.ilceAd = properties.getString("ilceAd");
        this.mevkii = properties.getString("mevkii");
        this.ilId = properties.getInt("ilId");
        this.durum = properties.getString("durum");
        this.ilceId = properties.getInt("ilceId");
        this.zeminKmdurum = properties.getString("zeminKmdurum");
        this.parselNo = properties.getString("parselNo");
        this.mahalleAd = properties.getString("mahalleAd");
        this.ozet = properties.getString("ozet");
        this.gittigiParselListe = properties.getString("gittigiParselListe");
        this.gittigiParselSebep = properties.getString("gittigiParselSebep");
        this.alan = properties.getString("alan");
        this.adaNo = properties.getString("adaNo");
        this.nitelik = properties.getString("nitelik");
        this.ilAd = properties.getString("ilAd");
        this.mahalleId = properties.getInt("mahalleId");
        this.pafta = properties.getString("pafta");
    }
}
