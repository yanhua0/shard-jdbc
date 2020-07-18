CREATE OR REPLACE FUNCTION public.create_table(
  table_num_in integer)
  RETURNS void
  LANGUAGE 'plpgsql'

  COST 100
  VOLATILE
AS $BODY$

declare

  v_idx integer := 0;
  v_strTable varchar :='';
  v_strSql varchar :='';
begin
  while v_idx < table_num_in loop

    v_strTable = CONCAT('table_one_', v_idx);
    v_strSql = 'create table '||v_strTable||'(id int8,phone varchar(20),back_one varchar(50),back_two varchar(50),back_three varchar(50));';
    v_idx = v_idx+1;
    EXECUTE v_strSql;
  end loop;
end
$BODY$;


SELECT public.create_table(23);
drop function public.create_table(
  table_num_in integer);