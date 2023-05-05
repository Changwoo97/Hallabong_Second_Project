DELETE FROM admin;
INSERT INTO admin (id, pw) VALUES ('smile', '12345');

DELETE FROM noti;
Insert into SYSTEM.NOTI (NO,TIT,CONT,REG_TM) values ('NOTI2023050522282536','개인정보 도용 피해를 예방하기 위해 비밀번호를 정기적으로 변경해주세요','안녕하세요.
최근 언론보도등에서 아이디 도용과 관련해서 이용자분들의 우려가 있으실 부분에 대해 다음과 같이 말씀드립니다.

피싱 사이트 또는 악성코드에 감염된 단말기 등을 통해 아이디, 비밀번호가 유출되거나 보안이 약한 일부 사이트에서 유출되는 경우 동일한 정보로 가입한 다른 사이트에서도 연달아 피해가 발생할 수 있습니다.
따라서 계정 정보 도용 피해를 예방하기 위해 하나의 아이디로 여러 사이트를 이용하시는 경우 비밀번호를 변경하고, 가능하다면 각 사이트별로 다른 비밀번호를 사용할 것을 권고하고 있습니다.  

이용자 여러분이 안심하고 서비스를 이용하실 수 있도록 노력해오고 있습니다. 앞으로도 이용자 개인정보 보호에 최선을 다하겠습니다.

감사합니다.',to_timestamp('23/05/05 22:28:25.368000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into SYSTEM.NOTI (NO,TIT,CONT,REG_TM) values ('NOTI2023050522333796','[공지] 불법 유통 의약품 소비자 주의','안녕하세요.
안전하고 건전한 온라인 유통 환경 조성을 위해 관련 법령에 저촉되는 상품을 판매하는 행위를 금지하고 있습니다.
 
특히 의약품의 경우, 약국 개설자(해당 약국에 근무하는 약사 또는 한약사를 포함)가 아니면 판매할 수 없으며 약국 또는 허용된 점포 이외의 장소에서도 판매할 수 없습니다.
 
또한 관련 법령에 따라 2022년 7월 21일부터는 전문의약품을 불법으로 구매한 자에게도 최대 100만원의 과태료가 부과될 수 있습니다.
* 근거 법령 : 약사법 제44조, 제47조의4, 제50조',to_timestamp('23/05/05 22:33:37.966000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into SYSTEM.NOTI (NO,TIT,CONT,REG_TM) values ('NOTI2023050522343432','[공지] 신종금융사기! 스미싱 주의바랍니다!!','최근 해외결제 관련 소비자보호센터 및 피해구제센터 등을 사칭하는 스미싱 사례가 증가하고 있습니다.
의심스러운 SMS 내 기재된 번호로 전화 연결하거나 인터넷주소(URL) 등을 클릭하지 않도록 주의하여 주시고, 피해 발생시 아래 신고기관으로 즉시 신고하시기 바랍니다.',to_timestamp('23/05/05 22:34:34.328000000','RR/MM/DD HH24:MI:SSXFF'));

DELETE FROM cat;
INSERT INTO cat (no, name) VALUES ('1', '채소');
INSERT INTO cat (no, name) VALUES ('2', '과일');
INSERT INTO cat (no, name) VALUES ('3', '육류');
INSERT INTO cat (no, name) VALUES ('4', '해산물');

DELETE FROM prod;
Insert into SYSTEM.PROD (NO,FS,NAME,COST,SP,S_IMG,L_IMG,CAT_NO,REG_TM) values ('PROD2023050314575378','Y','성주 꼬마 참외 900g (5~6입)',9900,11400,'1683094315522.png','1683094315523.png','2',to_timestamp('23/05/03 14:57:53.782000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into SYSTEM.PROD (NO,FS,NAME,COST,SP,S_IMG,L_IMG,CAT_NO,REG_TM) values ('PROD2023050315264769','Y','국산 블루베리 100g (특)',7181,7900,'1683095207661.png','1683095207662.png','2',to_timestamp('23/05/03 15:26:47.690000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into SYSTEM.PROD (NO,FS,NAME,COST,SP,S_IMG,L_IMG,CAT_NO,REG_TM) values ('PROD2023050314493258','Y','[KF365] DOLE 실속 바나나 1kg (필리핀)',3536,3890,'1683094914121.png','1683094914122.png','2',to_timestamp('23/05/03 14:49:32.584000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into SYSTEM.PROD (NO,FS,NAME,COST,SP,S_IMG,L_IMG,CAT_NO,REG_TM) values ('PROD2023050316062740','Y','[MPARK] 블랙라벨 고당도 오렌지 1.2kg (중대과/5입)',8900,7900,'1683097587384.png','1683097587386.png','2',to_timestamp('23/05/03 16:06:27.404000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into SYSTEM.PROD (NO,FS,NAME,COST,SP,S_IMG,L_IMG,CAT_NO,REG_TM) values ('PROD2023050520063934','Y','제주 흙당근 1kg',5000,5500,'1683284799338.png','1683284799341.png','1',to_timestamp('23/05/05 20:06:39.348000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into SYSTEM.PROD (NO,FS,NAME,COST,SP,S_IMG,L_IMG,CAT_NO,REG_TM) values ('PROD2023050520092124','Y','[KF]365 다다기오이 3',3173,3490,'1683285736705.png','1683284961247.png','1',to_timestamp('23/05/05 20:09:21.249000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into SYSTEM.PROD (NO,FS,NAME,COST,SP,S_IMG,L_IMG,CAT_NO,REG_TM) values ('PROD2023050522132908','Y','양파 1.5kg',4263,4690,'1683292434997.png','1683292471431.png','1',to_timestamp('23/05/05 22:13:29.086000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into SYSTEM.PROD (NO,FS,NAME,COST,SP,S_IMG,L_IMG,CAT_NO,REG_TM) values ('PROD2023050522190597','Y','깐대파 500g',2718,2990,'1683292745966.png','1683292745975.png','1',to_timestamp('23/05/05 22:19:05.976000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into SYSTEM.PROD (NO,FS,NAME,COST,SP,S_IMG,L_IMG,CAT_NO,REG_TM) values ('PROD2023050314284911','Y','[KF365] 유명산지 고당도사과 1.5kg (5~6입)',11730,12900,'1683094512372.png','1683094512373.png','2',to_timestamp('23/05/03 14:28:49.110000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into SYSTEM.PROD (NO,FS,NAME,COST,SP,S_IMG,L_IMG,CAT_NO,REG_TM) values ('PROD2023050514170987','Y','[KF365] 감자 1kg',5900,6490,'1683264940886.png','1683264940889.png','1',to_timestamp('23/05/05 14:17:09.871000000','RR/MM/DD HH24:MI:SSXFF'));

COMMIT;