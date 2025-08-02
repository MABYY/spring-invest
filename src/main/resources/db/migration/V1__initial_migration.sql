CREATE TABLE users
(
    id       bigint auto_increment primary key,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL
);

create table asset
(
    id            bigint auto_increment primary key,
    name          varchar(255) not null,
    ticker        varchar(255) not null,
    isin          varchar(255) not null,
    asset_type    varchar(255) not null,
    description   varchar(255)
);

create table portfolio
(
    id          bigint auto_increment primary key,
    name        VARCHAR(255)   NOT NULL,
    users_id    BIGINT NOT NULL,
    CONSTRAINT fk_portfolioUser
        FOREIGN KEY (users_id) REFERENCES users (id)
        -- keep user once portfolio is deleted
);

CREATE TABLE asset_List
(
    portfolio_id BIGINT NOT NULL,
    asset_id    BIGINT NOT NULL,
    CONSTRAINT pk_portfolioAsset PRIMARY KEY (portfolio_id, asset_id),
    CONSTRAINT fk_portfolio FOREIGN KEY (portfolio_id) REFERENCES portfolio (id) ON DELETE CASCADE,
    CONSTRAINT fk_portfolioAsset FOREIGN KEY (asset_id) REFERENCES asset (id)
);


