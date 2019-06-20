CREATE TABLE "public"."t_lonbon_event" (
  "id" serial ,
  "event_id" int4,
  "sender" int4,
  "receiver" int4,
  "accept_bc" varchar(255) COLLATE "pg_catalog"."default",
  "session_id" varchar(255) COLLATE "pg_catalog"."default",
  "broad_id" int4,
  "rd_file" varchar(255) COLLATE "pg_catalog"."default",
  "atm_num" int4,
  "state" int2,
  "create_time" timestamp(6),
  "upload_time" timestamp(6),
  CONSTRAINT "t_lonbon_event_pkey" PRIMARY KEY ("id")
);
ALTER TABLE "public"."t_lonbon_event"  OWNER TO "postgres";
COMMENT ON COLUMN "public"."t_lonbon_event"."event_id" IS '事件id';
COMMENT ON COLUMN "public"."t_lonbon_event"."sender" IS '发送端';
COMMENT ON COLUMN "public"."t_lonbon_event"."receiver" IS '接收端';
COMMENT ON COLUMN "public"."t_lonbon_event"."accept_bc" IS '广播接收端';
COMMENT ON COLUMN "public"."t_lonbon_event"."session_id" IS '会话标识';
COMMENT ON COLUMN "public"."t_lonbon_event"."broad_id" IS '广播组序';
COMMENT ON COLUMN "public"."t_lonbon_event"."rd_file" IS '录音文件名';
COMMENT ON COLUMN "public"."t_lonbon_event"."atm_num" IS 'Atm编号';
COMMENT ON COLUMN "public"."t_lonbon_event"."state" IS '事件状态0-未处理，1-上传';
COMMENT ON COLUMN "public"."t_lonbon_event"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_lonbon_event"."upload_time" IS '上传时间';