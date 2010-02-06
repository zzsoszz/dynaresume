
    create table T_AGENCE (
        AGC_ID_N integer not null,
        DATE_CREATION timestamp,
        DATE_MODIFICATION timestamp,
        UTILISATEUR_CREATION varchar(50),
        UTILISATEUR_MODIFICATION varchar(50),
        AGC_ADRESSE1_C varchar(50),
        AGC_ADRESSE2_C varchar(50),
        AGC_CODEPOSTAL_C varchar(6),
        AGC_FAX_C varchar(10),
        AGC_RAISONSOCIALE_C varchar(50),
        AGC_TELEPHONE_C varchar(10),
        AGC_VILLE_C varchar(100),
        primary key (AGC_ID_N)
    );

    create table T_COLLABORATEUR (
        COL_ID_N integer not null,
        DATE_CREATION timestamp,
        DATE_MODIFICATION timestamp,
        UTILISATEUR_CREATION varchar(50),
        UTILISATEUR_MODIFICATION varchar(50),
        COL_DATE_DEBUTCARRIERE_D timestamp,
        COL_DATENAISSANCE_D timestamp,
        COL_EMAIL_C varchar(50),
        COL_LIEUNAISSANCE_C varchar(100),
        COL_NOM_C varchar(100) not null,
        COL_PERMISB_B smallint,
        COL_PRENOM_C varchar(100) not null,
        COL_TITRE_C varchar(50),
        COL_ID_AGENCE_N integer not null,
        COL_ID_SITUATION_FAMILIALE_N integer,
        primary key (COL_ID_N)
    );

    create table T_COLLABORATEUR_COMPETENCE (
        RUB_ID_N integer not null,
        DATE_CREATION timestamp,
        DATE_MODIFICATION timestamp,
        UTILISATEUR_CREATION varchar(50),
        UTILISATEUR_MODIFICATION varchar(50),
        RUB_DESCRIPTION_C varchar(500),
        RUB_ID_TYPE_N integer not null,
        CCO_NIVEAU_N integer,
        CCO_ID_COMPETENCE_N integer not null,
        primary key (RUB_ID_N)
    );

    create table T_COLLABORATEUR_FORMATION (
        RUB_ID_N integer not null,
        DATE_CREATION timestamp,
        DATE_MODIFICATION timestamp,
        UTILISATEUR_CREATION varchar(50),
        UTILISATEUR_MODIFICATION varchar(50),
        RUB_DESCRIPTION_C varchar(500),
        RUB_ID_TYPE_N integer not null,
        FRM_DATE_DIPLOME_D timestamp,
        FRM_DIPLOME_C varchar(100),
        FRM_LIEU_C varchar(100),
        FRM_ORGANISME_C varchar(100),
        primary key (RUB_ID_N)
    );

    create table T_COLLABORATEUR_T_COLLABORATEUR_COMPETENCE (
        T_COLLABORATEUR_COL_ID_N integer not null,
        collaborateurCompetences_RUB_ID_N integer not null,
        unique (collaborateurCompetences_RUB_ID_N)
    );

    create table T_COMPETENCE (
        CPT_ID_N integer not null,
        DATE_CREATION timestamp,
        DATE_MODIFICATION timestamp,
        UTILISATEUR_CREATION varchar(50),
        UTILISATEUR_MODIFICATION varchar(50),
        CPT_NOM_C varchar(50) not null,
        CPT_ID_DOMAINE_N integer not null,
        primary key (CPT_ID_N)
    );

    create table T_DOMAINE (
        DOM_ID_N integer not null,
        DATE_CREATION timestamp,
        DATE_MODIFICATION timestamp,
        UTILISATEUR_CREATION varchar(50),
        UTILISATEUR_MODIFICATION varchar(50),
        DOM_NOM_C varchar(50) not null,
        primary key (DOM_ID_N)
    );

    create table T_MODELERTF (
        MDL_ID_N integer not null,
        DATE_CREATION timestamp,
        DATE_MODIFICATION timestamp,
        UTILISATEUR_CREATION varchar(50),
        UTILISATEUR_MODIFICATION varchar(50),
        MDL_CHEMIN_FICHIER_C varchar(100),
        MDL_NOM_C varchar(50) not null,
        MDL_VERSION_C varchar(20),
        MDL_ID_AGENCE_N integer not null,
        primary key (MDL_ID_N)
    );

    create table T_RUBRIQUE (
        RUB_ID_N integer not null,
        DATE_CREATION timestamp,
        DATE_MODIFICATION timestamp,
        UTILISATEUR_CREATION varchar(50),
        UTILISATEUR_MODIFICATION varchar(50),
        RUB_DESCRIPTION_C varchar(500),
        RUB_ID_TYPE_N integer not null,
        primary key (RUB_ID_N)
    );

    create table T_RUBRIQUE_TYPE (
        RBT_ID_N integer not null,
        DATE_CREATION timestamp,
        DATE_MODIFICATION timestamp,
        UTILISATEUR_CREATION varchar(50),
        UTILISATEUR_MODIFICATION varchar(50),
        RBT_NOM_C varchar(50) not null,
        primary key (RBT_ID_N)
    );

    create table T_TABLEREFERENCE (
        TRF_ID_N integer not null,
        DATE_CREATION timestamp,
        DATE_MODIFICATION timestamp,
        UTILISATEUR_CREATION varchar(50),
        UTILISATEUR_MODIFICATION varchar(50),
        TRF_TYPE_C varchar(50) not null,
        TRF_VALEUR_C varchar(50) not null,
        primary key (TRF_ID_N)
    );

    alter table T_COLLABORATEUR 
        add constraint FK233EB334729D6393 
        foreign key (COL_ID_AGENCE_N) 
        references T_AGENCE;

    alter table T_COLLABORATEUR 
        add constraint FK233EB33461F7E8E9 
        foreign key (COL_ID_SITUATION_FAMILIALE_N) 
        references T_TABLEREFERENCE;

    alter table T_COLLABORATEUR_COMPETENCE 
        add constraint FK795625965B222A4F319ddb4 
        foreign key (RUB_ID_TYPE_N) 
        references T_RUBRIQUE_TYPE;

    alter table T_COLLABORATEUR_COMPETENCE 
        add constraint FK319DDB4BF73F4D2 
        foreign key (CCO_ID_COMPETENCE_N) 
        references T_COMPETENCE;

    alter table T_COLLABORATEUR_FORMATION 
        add constraint FK795625965B222A4F8e82d546 
        foreign key (RUB_ID_TYPE_N) 
        references T_RUBRIQUE_TYPE;

    alter table T_COLLABORATEUR_T_COLLABORATEUR_COMPETENCE 
        add constraint FK20CE019F79B1289 
        foreign key (collaborateurCompetences_RUB_ID_N) 
        references T_COLLABORATEUR_COMPETENCE;

    alter table T_COLLABORATEUR_T_COLLABORATEUR_COMPETENCE 
        add constraint FK20CE019F1EE221D6 
        foreign key (T_COLLABORATEUR_COL_ID_N) 
        references T_COLLABORATEUR;

    alter table T_COMPETENCE 
        add constraint FKB8AB1C9483CF6F68 
        foreign key (CPT_ID_DOMAINE_N) 
        references T_DOMAINE;

    alter table T_MODELERTF 
        add constraint FKB093943D40A7E048 
        foreign key (MDL_ID_AGENCE_N) 
        references T_AGENCE;

    alter table T_RUBRIQUE 
        add constraint FK795625965B222A4F 
        foreign key (RUB_ID_TYPE_N) 
        references T_RUBRIQUE_TYPE;
