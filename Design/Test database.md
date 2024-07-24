# Customer Table

id | name |
--| ----|
1 | SadNguyen
2 | DuyTran


# Item Table
id | name |
--| -----|
1 | Rolex watch
2 | Duy's Clothes

# list_of_items

customer_id | item_id | quantity | code
-----------| -------- | ---------| ----- 
1 | 2 | 20 | 1_0x0001
2 | 3 | 1 | 2_0x0001
1 | 3 | 100 | 1_0x0001
2 | 3 | 3 | 2_0x0001
1 | 3 | 2 | 1_0x0002
1 | 2 | 10 | 1_0x0003


```sql
SELECT code 
FROM list_of_items
WHERE code NOT IN (SELECT code FROM purchased_order);

if
--> Return Null --> Create New Code 
else
---> Return 1 code ---> Use That Code
endif
```


# Purchased Order
// When Purchased => add code
code | date_of_purchased |
-----| ----------------- |
1_0x0001 | 21/02/2029:19:07:12 | 
2_0x0001 | 21/02/2029:19:07:40 | 
1_0x0002 | 21/02/2030:19:07:40 |
