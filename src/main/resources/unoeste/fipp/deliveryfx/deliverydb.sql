--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10
-- Dumped by pg_dump version 18.0

-- Started on 2026-11-03 08:16:36

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

--
-- TOC entry 196 (class 1259 OID 238407)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    cat_id integer NOT NULL,
    cat_nome character varying(20)
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 238410)
-- Name: categoria_cat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categoria_cat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categoria_cat_id_seq OWNER TO postgres;

--
-- TOC entry 2872 (class 0 OID 0)
-- Dependencies: 197
-- Name: categoria_cat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_cat_id_seq OWNED BY public.categoria.cat_id;


--
-- TOC entry 198 (class 1259 OID 238417)
-- Name: item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item (
    pro_id integer NOT NULL,
    ped_id integer NOT NULL,
    it_quant integer,
    it_valor numeric(10,2)
);


ALTER TABLE public.item OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 238480)
-- Name: marca; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.marca (
    mar_id integer NOT NULL,
    mar_nome character varying(20)
);


ALTER TABLE public.marca OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 238478)
-- Name: marca_mar_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.marca_mar_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.marca_mar_id_seq OWNER TO postgres;

--
-- TOC entry 2873 (class 0 OID 0)
-- Dependencies: 205
-- Name: marca_mar_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.marca_mar_id_seq OWNED BY public.marca.mar_id;


--
-- TOC entry 199 (class 1259 OID 238420)
-- Name: pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pedido (
    ped_id integer NOT NULL,
    ped_data date,
    ped_clinome character varying(50),
    ped_clifone character varying(15),
    ped_total numeric(10,2),
    tpg_id integer,
    ped_local text,
    ped_numero_res character varying(10),
    st_id integer
);


ALTER TABLE public.pedido OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 238423)
-- Name: pedido_ped_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pedido_ped_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pedido_ped_id_seq OWNER TO postgres;

--
-- TOC entry 2874 (class 0 OID 0)
-- Dependencies: 200
-- Name: pedido_ped_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pedido_ped_id_seq OWNED BY public.pedido.ped_id;


--
-- TOC entry 201 (class 1259 OID 238425)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produto (
    pro_id integer NOT NULL,
    pro_nome character varying(30),
    pro_preco numeric(10,2),
    cat_id integer,
    pro_estoque integer,
    mar_id integer
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 238431)
-- Name: produto_pro_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produto_pro_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.produto_pro_id_seq OWNER TO postgres;

--
-- TOC entry 2875 (class 0 OID 0)
-- Dependencies: 202
-- Name: produto_pro_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produto_pro_id_seq OWNED BY public.produto.pro_id;


--
-- TOC entry 208 (class 1259 OID 255022)
-- Name: status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status (
    st_id integer NOT NULL,
    st_descr character varying(25),
    st_obs character varying(80),
    st_sucesso boolean
);


ALTER TABLE public.status OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 255020)
-- Name: status_st_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.status_st_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.status_st_id_seq OWNER TO postgres;

--
-- TOC entry 2876 (class 0 OID 0)
-- Dependencies: 207
-- Name: status_st_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.status_st_id_seq OWNED BY public.status.st_id;


--
-- TOC entry 203 (class 1259 OID 238433)
-- Name: tipo_pagamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_pagamento (
    tpg_id integer NOT NULL,
    tpg_nome character varying(20)
);


ALTER TABLE public.tipo_pagamento OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 238436)
-- Name: tipo_pagamento_tpg_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_pagamento_tpg_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tipo_pagamento_tpg_id_seq OWNER TO postgres;

--
-- TOC entry 2877 (class 0 OID 0)
-- Dependencies: 204
-- Name: tipo_pagamento_tpg_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_pagamento_tpg_id_seq OWNED BY public.tipo_pagamento.tpg_id;


--
-- TOC entry 2706 (class 2604 OID 238438)
-- Name: categoria cat_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria ALTER COLUMN cat_id SET DEFAULT nextval('public.categoria_cat_id_seq'::regclass);


--
-- TOC entry 2710 (class 2604 OID 238483)
-- Name: marca mar_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca ALTER COLUMN mar_id SET DEFAULT nextval('public.marca_mar_id_seq'::regclass);


--
-- TOC entry 2707 (class 2604 OID 238440)
-- Name: pedido ped_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido ALTER COLUMN ped_id SET DEFAULT nextval('public.pedido_ped_id_seq'::regclass);


--
-- TOC entry 2708 (class 2604 OID 238441)
-- Name: produto pro_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto ALTER COLUMN pro_id SET DEFAULT nextval('public.produto_pro_id_seq'::regclass);


--
-- TOC entry 2711 (class 2604 OID 255025)
-- Name: status st_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status ALTER COLUMN st_id SET DEFAULT nextval('public.status_st_id_seq'::regclass);


--
-- TOC entry 2709 (class 2604 OID 238442)
-- Name: tipo_pagamento tpg_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_pagamento ALTER COLUMN tpg_id SET DEFAULT nextval('public.tipo_pagamento_tpg_id_seq'::regclass);


--
-- TOC entry 2853 (class 0 OID 238407)
-- Dependencies: 196
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categoria VALUES (1, 'refrigerante');
INSERT INTO public.categoria VALUES (2, 'cerveja');
INSERT INTO public.categoria VALUES (3, 'destilado');
INSERT INTO public.categoria VALUES (5, 'vinhos nacionais');
INSERT INTO public.categoria VALUES (6, 'sucos naturais');
INSERT INTO public.categoria VALUES (7, 'energéticos');
INSERT INTO public.categoria VALUES (8, 'água');


--
-- TOC entry 2855 (class 0 OID 238417)
-- Dependencies: 198
-- Data for Name: item; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.item VALUES (4, 1, 1, 15.00);
INSERT INTO public.item VALUES (5, 1, 2, 10.00);
INSERT INTO public.item VALUES (9, 23, 5, 62.50);
INSERT INTO public.item VALUES (6, 23, 1, 8.00);
INSERT INTO public.item VALUES (5, 8, 2, 10.00);
INSERT INTO public.item VALUES (8, 8, 1, 15.00);
INSERT INTO public.item VALUES (2, 8, 10, 85.00);
INSERT INTO public.item VALUES (6, 8, 2, 16.00);
INSERT INTO public.item VALUES (7, 24, 1, 3.50);
INSERT INTO public.item VALUES (9, 24, 1, 12.50);
INSERT INTO public.item VALUES (7, 9, 2, 7.00);
INSERT INTO public.item VALUES (6, 9, 4, 32.00);
INSERT INTO public.item VALUES (8, 24, 3, 45.00);
INSERT INTO public.item VALUES (8, 10, 3, 45.00);
INSERT INTO public.item VALUES (9, 25, 3, 37.50);
INSERT INTO public.item VALUES (5, 25, 4, 20.00);
INSERT INTO public.item VALUES (7, 11, 3, 10.50);
INSERT INTO public.item VALUES (2, 11, 5, 42.50);
INSERT INTO public.item VALUES (8, 11, 1, 15.00);
INSERT INTO public.item VALUES (1, 13, 3, 75.00);
INSERT INTO public.item VALUES (9, 27, 3, 37.50);
INSERT INTO public.item VALUES (2, 27, 14, 119.00);
INSERT INTO public.item VALUES (8, 28, 3, 45.00);
INSERT INTO public.item VALUES (8, 14, 4, 60.00);
INSERT INTO public.item VALUES (7, 14, 2, 7.00);
INSERT INTO public.item VALUES (9, 14, 1, 12.50);
INSERT INTO public.item VALUES (9, 29, 2, 25.00);
INSERT INTO public.item VALUES (7, 29, 7, 24.50);
INSERT INTO public.item VALUES (9, 15, 4, 50.00);
INSERT INTO public.item VALUES (9, 16, 3, 37.50);
INSERT INTO public.item VALUES (4, 16, 1, 15.00);
INSERT INTO public.item VALUES (9, 18, 2, 25.00);
INSERT INTO public.item VALUES (7, 18, 6, 21.00);
INSERT INTO public.item VALUES (4, 18, 3, 45.00);
INSERT INTO public.item VALUES (9, 19, 4, 50.00);
INSERT INTO public.item VALUES (7, 19, 1, 3.50);
INSERT INTO public.item VALUES (9, 21, 3, 37.50);
INSERT INTO public.item VALUES (7, 21, 1, 3.50);
INSERT INTO public.item VALUES (8, 22, 3, 45.00);
INSERT INTO public.item VALUES (6, 22, 3, 24.00);


--
-- TOC entry 2863 (class 0 OID 238480)
-- Dependencies: 206
-- Data for Name: marca; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.marca VALUES (2, 'Heineken');
INSERT INTO public.marca VALUES (3, 'Askov');
INSERT INTO public.marca VALUES (4, 'Coca Cola');
INSERT INTO public.marca VALUES (5, 'Aguardente 51');
INSERT INTO public.marca VALUES (1, 'Antarctica');
INSERT INTO public.marca VALUES (6, 'Spaten');


--
-- TOC entry 2856 (class 0 OID 238420)
-- Dependencies: 199
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pedido VALUES (1, '2026-10-15', 'Clotilda Nunes', '18 99995-9688', 15.00, 1, 'Av Brasil, Vila Industrial, Presidente Prudente, SP', '2000', 1);
INSERT INTO public.pedido VALUES (8, '2026-12-10', 'Hilmo May', '(18) 88888-8888', 126.00, 4, 'Rua Seis, Vila Maria, Presidente Prudente/SP', '100', 1);
INSERT INTO public.pedido VALUES (9, '2026-12-10', 'Oswald Krug', '(18) 88888-8888', 39.00, 2, 'Avenida Brasil, Jardim Bela Daria, Presidente Prudente/SP', '100', 1);
INSERT INTO public.pedido VALUES (24, '2026-12-10', 'Titica Gayer', '(18) 99999-9999', 61.00, 3, 'Rua Agostinho da Cruz Faustino, Parque Higienópolis, Presidente Prudente/SP', '200', 1);
INSERT INTO public.pedido VALUES (11, '2026-12-10', 'Fabin Bebin', '(18) 66565-6566', 68.00, 1, 'Rua Isao Anzai, Parque Residencial Pardais, Presidente Prudente/SP', '100', 1);
INSERT INTO public.pedido VALUES (13, '2026-12-10', 'Micaeli', '(18) 81818-1818', 75.00, 1, 'Rua José Vieira, Parque Residencial Pardais, Presidente Prudente/SP', '100', 1);
INSERT INTO public.pedido VALUES (14, '2026-12-10', 'Jão Pinga', '(18) 55656-5656', 79.50, 1, 'Rua Neide Pimenta Tolomei, Parque Residencial Pardais, Presidente Prudente/SP', '15800', 1);
INSERT INTO public.pedido VALUES (15, '2026-12-10', 'Laura Antonio', '(11) 11111-1111', 0.00, 2, 'Rua Epifânio Peixoto de Lima, Parque Residencial Pardais, Presidente Prudente/SP', '100', 1);
INSERT INTO public.pedido VALUES (16, '2026-12-10', 'Lauana Prata', '(18) 88888-8888', 52.50, 2, 'Rua Conceição Bacarin, Parque Residencial Pardais, Presidente Prudente/SP', '500', 2);
INSERT INTO public.pedido VALUES (19, '2026-12-10', 'Enzinos', '(18) 8888-8888', 53.50, 2, 'Rua Octaviano Vieira Noia, Parque Residencial Pardais, Presidente Prudente/SP', '522', 1);
INSERT INTO public.pedido VALUES (23, '2026-12-10', 'Rotta Rui', '(18) 88888-8888', 70.50, 3, 'Rua Isao Anzai, Parque Residencial Pardais, Presidente Prudente/SP', '200', 1);
INSERT INTO public.pedido VALUES (10, '2026-12-10', 'Cristim Bebin', '(18) 88888-8888', 0.00, 2, 'Rua José Henrique Bugalho Filho, Parque Residencial Pardais, Presidente Prudente/SP', '500', 1);
INSERT INTO public.pedido VALUES (18, '2026-12-10', 'Jamila Silva', '(11) 11111-1111', 91.00, 2, 'Rua Orlando Moreti, Parque Residencial Pardais, Presidente Prudente/SP', '100', 2);
INSERT INTO public.pedido VALUES (22, '2026-12-10', 'Rique Rone', '(18) 11111-1112', 69.00, 2, 'Rua Agostinho da Cruz Faustino, Parque Higienópolis, Presidente Prudente/SP', '500', 1);
INSERT INTO public.pedido VALUES (29, '2026-12-11', 'Lacerds Kenji', '(18) 88888-8888', 49.50, 6, 'Rua G, Parque Higienópolis, Presidente Prudente/SP', '10', 6);
INSERT INTO public.pedido VALUES (21, '2026-12-10', 'Marinha Ju', '(18) 81818-1811', 41.00, 2, 'Rua Conceição Bacarin, Parque Residencial Pardais, Presidente Prudente/SP', '100', 1);
INSERT INTO public.pedido VALUES (25, '2026-12-11', 'Situs Inno', '(18) 88888-8888', 57.50, 2, 'Rua Paulo Cardia Amorim, Parque Residencial Pardais, Presidente Prudente/SP', '200', 1);
INSERT INTO public.pedido VALUES (28, '2026-12-11', 'Otavu Jumila', '(18) 88888-8888', 45.00, 2, 'Rua Pedro Guarinon, Parque Residencial Pardais, Presidente Prudente/SP', '10', 6);
INSERT INTO public.pedido VALUES (27, '2026-12-11', 'Nunilo', '(18) 88888-8888', 156.50, 1, 'Avenida Irineu Sesti, Parque Residencial Pardais, Presidente Prudente/SP', '152', 6);


--
-- TOC entry 2858 (class 0 OID 238425)
-- Dependencies: 201
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.produto VALUES (1, 'Vodka litro', 25.00, 3, 10, 3);
INSERT INTO public.produto VALUES (2, 'Cerveja long neck', 8.50, 2, 240, 2);
INSERT INTO public.produto VALUES (3, 'Cerveja garrafa 600ml', 11.50, 2, 240, 1);
INSERT INTO public.produto VALUES (4, 'Cerveja garrafa 600ml', 15.00, 2, 48, 2);
INSERT INTO public.produto VALUES (5, 'Coca Lata lata 350ml', 5.00, 1, 80, 4);
INSERT INTO public.produto VALUES (6, 'Cerveja Lata 350ml', 8.00, 2, 10, 2);
INSERT INTO public.produto VALUES (7, 'Guaraná lata 350ml', 3.50, 1, 5, 1);
INSERT INTO public.produto VALUES (8, 'Pinga coquinho 1l', 15.00, 3, 2, 3);
INSERT INTO public.produto VALUES (9, 'Suco de Laranja 900ml', 12.50, 6, 24, 1);


--
-- TOC entry 2865 (class 0 OID 255022)
-- Dependencies: 208
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.status VALUES (1, 'Entregue', 'pedido entregue normalmente', true);
INSERT INTO public.status VALUES (2, 'Entregue com atraso', 'Houve atraso na entrega do pedido', true);
INSERT INTO public.status VALUES (3, 'Entrega recusada', 'O cliente recusou no momento da entrega', false);
INSERT INTO public.status VALUES (4, 'Cancelado pelo cliente', 'O cliente cancelou o pedido', false);
INSERT INTO public.status VALUES (5, 'Cancelado pela empresa', 'O pedido foi cancelado pela empresa', false);
INSERT INTO public.status VALUES (6, 'Pendente', 'O pedido ainda não foi entregue', false);
INSERT INTO public.status VALUES (7, 'Devolvido', 'Pedido devolvido pelo cliente/cliente não encontrado', true);


--
-- TOC entry 2860 (class 0 OID 238433)
-- Dependencies: 203
-- Data for Name: tipo_pagamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_pagamento VALUES (1, 'PIX');
INSERT INTO public.tipo_pagamento VALUES (2, 'Dinheiro');
INSERT INTO public.tipo_pagamento VALUES (3, 'Cartão Débito');
INSERT INTO public.tipo_pagamento VALUES (4, 'Cartão Crédito');
INSERT INTO public.tipo_pagamento VALUES (5, 'Cheque');
INSERT INTO public.tipo_pagamento VALUES (6, 'Fiado');
INSERT INTO public.tipo_pagamento VALUES (7, 'Ticket Alimentação');


--
-- TOC entry 2878 (class 0 OID 0)
-- Dependencies: 197
-- Name: categoria_cat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categoria_cat_id_seq', 8, true);


--
-- TOC entry 2879 (class 0 OID 0)
-- Dependencies: 205
-- Name: marca_mar_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.marca_mar_id_seq', 7, true);


--
-- TOC entry 2880 (class 0 OID 0)
-- Dependencies: 200
-- Name: pedido_ped_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pedido_ped_id_seq', 33, true);


--
-- TOC entry 2881 (class 0 OID 0)
-- Dependencies: 202
-- Name: produto_pro_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produto_pro_id_seq', 9, true);


--
-- TOC entry 2882 (class 0 OID 0)
-- Dependencies: 207
-- Name: status_st_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.status_st_id_seq', 7, true);


--
-- TOC entry 2883 (class 0 OID 0)
-- Dependencies: 204
-- Name: tipo_pagamento_tpg_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_pagamento_tpg_id_seq', 7, true);


--
-- TOC entry 2713 (class 2606 OID 238444)
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (cat_id);


--
-- TOC entry 2715 (class 2606 OID 238448)
-- Name: item item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (pro_id, ped_id);


--
-- TOC entry 2723 (class 2606 OID 238485)
-- Name: marca marca_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (mar_id);


--
-- TOC entry 2717 (class 2606 OID 238450)
-- Name: pedido pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (ped_id);


--
-- TOC entry 2719 (class 2606 OID 238452)
-- Name: produto produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (pro_id);


--
-- TOC entry 2725 (class 2606 OID 255027)
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (st_id);


--
-- TOC entry 2721 (class 2606 OID 238454)
-- Name: tipo_pagamento tipo_pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_pagamento
    ADD CONSTRAINT tipo_pagamento_pkey PRIMARY KEY (tpg_id);


--
-- TOC entry 2726 (class 2606 OID 238455)
-- Name: item item_ped_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_ped_id_fkey FOREIGN KEY (ped_id) REFERENCES public.pedido(ped_id) NOT VALID;


--
-- TOC entry 2727 (class 2606 OID 238460)
-- Name: item item_pro_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pro_id_fkey FOREIGN KEY (pro_id) REFERENCES public.produto(pro_id);


--
-- TOC entry 2728 (class 2606 OID 255036)
-- Name: pedido pedido_st_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_st_id_fkey FOREIGN KEY (st_id) REFERENCES public.status(st_id) NOT VALID;


--
-- TOC entry 2729 (class 2606 OID 238465)
-- Name: pedido pedido_tpg_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_tpg_id_fkey FOREIGN KEY (tpg_id) REFERENCES public.tipo_pagamento(tpg_id) NOT VALID;


--
-- TOC entry 2730 (class 2606 OID 238470)
-- Name: produto produto_cat_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES public.categoria(cat_id);


--
-- TOC entry 2731 (class 2606 OID 238486)
-- Name: produto produto_mar_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_mar_id_fkey FOREIGN KEY (mar_id) REFERENCES public.marca(mar_id) NOT VALID;


--
-- TOC entry 2871 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2026-11-03 08:16:36

--
-- PostgreSQL database dump complete
--

