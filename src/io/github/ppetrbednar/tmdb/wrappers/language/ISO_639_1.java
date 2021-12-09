package io.github.ppetrbednar.tmdb.wrappers.language;

/**
 * List of language shortcuts in norm ISO 639-1.
 *
 * @author Petr Bednář
 */
public enum ISO_639_1 {

    AA("Afar"),
    AB("Abkhazian"),
    AE("Avestan"),
    AF("Afrikaans"),
    AK("Akan"),
    AM("Amharic"),
    AN("Aragonese"),
    AR("Arabic"),
    AS("Assamese"),
    AV("Avaric"),
    AY("Aymara"),
    AZ("Azerbaijani"),
    BA("Bashkir"),
    BE("Belarusian"),
    BG("Bulgarian"),
    BH("Bihari languages"),
    BI("Bislama"),
    BM("Bambara"),
    BN("Bengali"),
    BO("Tibetan"),
    BR("Breton"),
    BS("Bosnian"),
    CA("Catalan, Valencian"),
    CE("Chechen"),
    CH("Chamorro"),
    CO("Corsican"),
    CR("Cree"),
    CS("Czech"),
    CU("Church Slavic, Slavonic, Old Bulgarian"),
    CV("Chuvash"),
    CY("Welsh"),
    DA("Danish"),
    DE("German"),
    DV("Divehi, Dhivehi, Maldivian"),
    DZ("Dzongkha"),
    EE("Ewe"),
    EL("Greek, Modern (1453-)"),
    EN("English"),
    EO("Esperanto"),
    ES("Spanish, Castilian"),
    ET("Estonian"),
    EU("Basque"),
    FA("Persian"),
    FF("Fulah"),
    FI("Finnish"),
    FJ("Fijian"),
    FO("Faroese"),
    FR("French"),
    FY("Western Frisian"),
    GA("Irish"),
    GD("Gaelic, Scottish Gaelic"),
    GL("Galician"),
    GN("Guarani"),
    GU("Gujarati"),
    GV("Manx"),
    HA("Hausa"),
    HE("Hebrew"),
    HI("Hindi"),
    HO("Hiri Motu"),
    HR("Croatian"),
    HT("Haitian, Haitian Creole"),
    HU("Hungarian"),
    HY("Armenian"),
    HZ("Herero"),
    IA("Interlingua"),
    ID("Indonesian"),
    IE("Interlingue, Occidental"),
    IG("Igbo"),
    II("Sichuan Yi, Nuosu"),
    IK("Inupiaq"),
    IO("Ido"),
    IS("Icelandic"),
    IT("Italian"),
    IU("Inuktitut"),
    JA("Japanese"),
    JV("Javanese"),
    KA("Georgian"),
    KG("Kongo"),
    KI("Kikuyu, Gikuyu"),
    KJ("Kuanyama, Kwanyama"),
    KK("Kazakh"),
    KL("Kalaallisut, Greenlandic"),
    KM("Central Khmer"),
    KN("Kannada"),
    KO("Korean"),
    KR("Kanuri"),
    KS("Kashmiri"),
    KU("Kurdish"),
    KV("Komi"),
    KW("Cornish"),
    KY("Kirghiz, Kyrgyz"),
    LA("Latin"),
    LB("Luxembourgish, Letzeburgesch"),
    LG("Ganda"),
    LI("Limburgan, Limburger, Limburgish"),
    LN("Lingala"),
    LO("Lao"),
    LT("Lithuanian"),
    LU("Luba-Katanga"),
    LV("Latvian"),
    MG("Malagasy"),
    MH("Marshallese"),
    MI("Maori"),
    MK("Macedonian"),
    ML("Malayalam"),
    MN("Mongolian"),
    MR("Marathi"),
    MS("Malay"),
    MT("Maltese"),
    MY("Burmese"),
    NA("Nauru"),
    NB("Norwegian Bokmål"),
    ND("Ndebele North, North Ndebele"),
    NE("Nepali"),
    NG("Ndonga"),
    NL("Dutch, Flemish"),
    NN("Norwegian Nynorsk"),
    NO("Norwegian"),
    NR("Ndebele South, South Ndebele"),
    NV("Navajo, Navaho"),
    NY("Chichewa, Chewa, Nyanja"),
    OC("Occitan (post 1500)"),
    OJ("Ojibwa"),
    OM("Oromo"),
    OR("Oriya"),
    OS("Ossetian, Ossetic"),
    PA("Panjabi, Punjabi"),
    PI("Pali"),
    PL("Polish"),
    PS("Pushto, Pashto"),
    PT("Portuguese"),
    QU("Quechua"),
    RM("Romansh"),
    RN("Rundi"),
    RO("Romanian, Moldavian, Moldovan"),
    RU("Russian"),
    RW("Kinyarwanda"),
    SA("Sanskrit"),
    SC("Sardinian"),
    SD("Sindhi"),
    SE("Northern Sami"),
    SG("Sango"),
    SI("Sinhala, Sinhalese"),
    SK("Slovak"),
    SL("Slovenian"),
    SM("Samoan"),
    SN("Shona"),
    SO("Somali"),
    SQ("Albanian"),
    SR("Serbian"),
    SS("Swati"),
    ST("Sotho, Southern"),
    SU("Sundanese"),
    SV("Swedish"),
    SW("Swahili"),
    TA("Tamil"),
    TE("Telugu"),
    TG("Tajik"),
    TH("Thai"),
    TI("Tigrinya"),
    TK("Turkmen"),
    TL("Tagalog"),
    TN("Tswana"),
    TO("Tonga (Tonga Islands)"),
    TR("Turkish"),
    TS("Tsonga"),
    TT("Tatar"),
    TW("Twi"),
    TY("Tahitian"),
    UG("Uighur, Uyghur"),
    UK("Ukrainian"),
    UR("Urdu"),
    UZ("Uzbek"),
    VE("Venda"),
    VI("Vietnamese"),
    VO("Volapük"),
    WA("Walloon"),
    WO("Wolof"),
    XH("Xhosa"),
    YI("Yiddish"),
    YO("Yoruba"),
    ZA("Zhuang, Chuang"),
    ZH("Chinese"),
    ZU("Zulu"),
    UNKNOWN("Unknown");

    private final String language;

    private ISO_639_1(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    /**
     * Converts any supported language shortcut from ISO 639-1.
     *
     * @param shortcut Lanuage shortcut
     * @return Language
     */
    public static ISO_639_1 getValueOf(String shortcut) {
        
        if (shortcut == null || shortcut.length() != 2) {
            return UNKNOWN;
        }

        try {
            return valueOf(shortcut.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return UNKNOWN;
        }
    }
}
