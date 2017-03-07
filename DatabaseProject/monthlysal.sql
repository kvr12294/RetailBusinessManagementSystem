/* ************************************************************* Monthly sale ****************************************************** */
create or replace package reportMonthlySale as
type ref_cursor is ref cursor;
function getmonthlySale(product_id in products.pid%TYPE)
return ref_cursor;
end;
/
show errors 
create or replace package body reportMonthlySale as
function getmonthlySale(product_id in products.pid%TYPE)
return ref_cursor is
RS ref_cursor;
begin
open RS for
select products.pname,to_char(purchases.ptime,'MON'), to_char(purchases.ptime,'yyyy'),sum(purchases.total_price), sum(purchases.total_price)/sum(purchases.qty) averageSale
from products,purchases where purchases.pid = product_id and purchases.pid = products.pid group by to_char(purchases.ptime,'MON'),to_char(purchases.ptime,'yyyy'), products.pname order by products.pname;
return RS;
end;
end;
/
show errors


/* for execution
select reportMonthlySale.getmonthlySale(&product_id) from dual;
*/
/* *********************************************************************************************************************************** */