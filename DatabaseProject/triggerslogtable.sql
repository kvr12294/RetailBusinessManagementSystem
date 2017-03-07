/* ******************************************************* Triggers to add tuples in log table *************************************** */
create or replace trigger insertVal
after insert on purchases 
for each row
username employees.ename%TYPE;
begin
select username into user from logs where e_id = employees.eid;
insert into logs(log,who,otime,table_name,operation,key_value) values (log#.nextval,user,sysdate,'purchases','Insert',:new.pur);
end;
/
show errors

create or replace trigger updateQoh
after update of qoh on products for each row
begin
insert into logs(log,who,otime,table_name,operation,key_value) values (log#.nextval,'user',sysdate,'products','Update',:new.pid);
end;
/
show errors

create or replace trigger updateVisitsmade
after update of visits_made on customers for each row
begin
insert into logs(log,who,otime,table_name,operation,key_value) values (log#.nextval,'user',sysdate,'customers','Update',:new.cid);
end;
/
show errors

create or replace trigger insertSupply
after insert on supply for each row
begin
insert into logs(log,who,otime,table_name,operation,key_value) values (log#.nextval,'user',sysdate,'supply','Insert',:new.sup);
end;
/
show errors	

/* ************************************************************************************************************************************ */