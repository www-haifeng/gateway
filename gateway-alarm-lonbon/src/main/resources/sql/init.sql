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




INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10001', 'lb_answer', 'post', '接听');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10002', 'lb_call', 'post', '发起呼叫');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10003', 'lb_hangUp', 'post', '挂断');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10004', 'lb_start_broadcast', 'post', '开始 广播');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10005', 'lb_stop_broadcast', 'post', '停止 广播');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10006', 'lb_start_broadcast_file', 'post', '开启语音文件广播');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10007', 'lb_stop_broadcast_file', 'post', '停止语音文件广播');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10008', 'lb_getTerminalInfos', 'post', '对讲服务器终端信息');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10009', 'lb_getTerminalInfo', 'post', '指定终端信息');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10010', 'lb_get_all_master', 'post', ' 查询全部主机');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10011', 'lb_get_terminal_from_master', 'post', '获取指定主机下所有在线分终端');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10012', 'lb_get_online_master', 'post', '查询全部在线主机');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10013', 'lb_get_terminal_online_from_master', 'post', '查询主机下在线分机');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10014', 'lb_get_state_from_terminal', 'post', '查询终端在线状态');
INSERT INTO "public"."t_msg_info"( "factory_id", "msg_type", "msg_id", "interface_id", "request_type", "describe") VALUES ( 5, 1, '10015', 'lb_get_error_info', 'post', ' 获取错误信息');