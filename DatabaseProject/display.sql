create or replace package refcursor_jdbc1 as
type ref_cursor1 is ref cursor;
function getemployees
return ref_cursor1;

function getcustomers
return ref_cursor1;

function getproducts
return ref_cursor1;

function getsuppliers
return ref_cursor1;

function getsupply
return ref_cursor1;

function getpurchase
return ref_cursor1;

end;
/
show errors

create or replace package body refcursor_jdbc1 as
function getemployees
return ref_cursor1 is
rc ref_cursor1;
begin
open rc for
select * from employees;
return rc;
end;

function getcustomers
return ref_cursor1 is
rc ref_cursor1;
begin
open rc for
select * from customers;
return rc;
end;

function getproducts
return ref_cursor1 is
rc ref_cursor1;
begin
open rc for
select * from products;
return rc;
end;

function getsuppliers
return ref_cursor1 is
rc ref_cursor1;
begin
open rc for
select * from suppliers;
return rc;
end;

function getsupply
return ref_cursor1 is
rc ref_cursor1;
begin
open rc for
select * from supply;
return rc;
end;

function getpurchase
return ref_cursor1 is
rc ref_cursor1;
begin
open rc for
select * from purchases;
return rc;
end;
end;
/
show errors