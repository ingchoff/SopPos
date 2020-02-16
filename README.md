
# SopPos
Microservice ระบบ Pos (Point of Sale)  
![Imgur](https://www.mx7.com/i/0bc/MN3Dwf.jpg)
![Imgur](https://i.imgur.com/lRXRwqh.jpg)
### Presentation Slide
[Presentation Slide](https://docs.google.com/presentation/d/1bG-yr2pnULOYmUp5Ueyw42R4IQsZm4aFT8VgGmsZxKk/edit?usp=sharing)
## :question: ระบบ POS คืออะไร
**ระบบ POS** ย่อมาจาก Point of Sale System หรือ ระบบขายหน้าร้าน ซึ่งนำหลักการของเครื่องคิดเงิน (Cash Register) มาเขียนโปรแกรมพัฒนาบนคอมพิวเตอร์
แล้วเพิ่มเติมความสามารถต่างๆที่เครื่องเก็บเงินทำไม่ได้ เช่น สามารถตัดสต็อกได้ ดูความเคลื่อนไหวต่างๆของสินค้า สรุปข้อมูลยอดขาย เป็นต้น
## :trophy: Our Service
* Authentication
  * เป็นฟังก์ชัน Auth แบ่ง permission ของ role(Manager, Seller)
* CRUD Stock
  * เป็นฟังก์ชันเพิ่ม แก้ไข ลบ และดู Stock
* CRUD Products
  * เป็นฟังก์ชันเพิ่ม แก้ไข ลบ และดู Product
* Order
  * เป็นฟังก์ชันสรุปใบเสร็จ เก็บรายรับ-รายจ่าย เข้า Account
* Account
  * สรุปข้อมูลการซื้อวัตถุดิบ(Stock)
  * สรุปยอดขายสินค้า รายวัน/เดือน/ปี
  
## Route Service
- Stock port 8096
```
  - /api/stock เรียกดู stock (GET)
  - /api/stock/create เพิ่ม stock (POST)
  - /api/stock?name=xx เรียกดู อัพเดท ลบ จากชื่อ stock โดยใช้ GET PUT DELETE ตามลำดับ
```
- Product port 8093
```
   - /api/products เรียกดู product (GET)
   - /api/products/add เพิ่ม product (POST)
   - /api/products?name=xx เรียกดู อัพเดท ลบ จากชื่อ product โดยใช้ GET PUT DELETE ตามลำดับ
```
 - Productlist (service เอาไว้เชื่อม stock กับ product) port 8094
```
    - /api/product-stock เรียกดู productlist ทั้งหมด (GET)
    - /api/product-stock/add เพิ่ม productlist (POST)
    - /api/product-stock?name=xx เรียกดู อัพเดท ลบ จากชื่อ productlist โดยใช้ GET PUT DELETE ตามลำดับ
    - /api/product-stock?name=xx ลบจากชื่อ product (DELETE)
```
 - Order port 8099
```
    - /api/order/create สร้าง order
    - /api/order/get เรียกดู order
    - /api/order/get/date/{date} เรียกดูตามวัน
    - /api/order/get/month/{month} เรียกดูตามเดือน
    - /api/order/get/year/{year} เรียกดูตามปี
    - /api/order/get/update route นี้เอาไว้เรียกจาก invoice service(create invoice) ผ่าน RestTemplate เพื่อไว้อัพเดต status order เป็น "done" หลังจากสร้าง invoice แล้ว
```
 - OrderList port 8102
```
    - /api/orderlist/create สร้าง orderlist
    - /api/orderlist/get เรียกดู orderlist
    - /api/orderlist/get/{id} เรียกดู orderlist ตาม id
    - /api/orderlist/creates สร้าง orderlist หลาย orders
    - /api/orderlist/get/name/{name} เรียกดู orderlist ตามชื่อ
    - /api/orderlist/get/oid/{oid} เรียกดู orderlist ตาม oid
    - /api/orderlist/update อัพเดท
    - /api/orderlist/delete/{id} ลบตาม id
    - /api/orderlist/delete/oid/{oid} ลบตาม oid
```
- Invoice port 8095
```
    - /api/invoice เรียกดู invoice ทั้งหมด
    - /api/invoice/{id} เรียกดู invoice ตาม id
    - /api/invoice/create สร้าง invoice พอสร้างเสร็จจะเรียก orderservice เพื่ออัพเดต status order เป็น "done"ให้อัตโนมัติ
```
 - Account port 8097
```
    - /api/account/get/profit/all หากำไรทั้งหมด
    - /api/account/get/profit/date/{date} หากำไรรายวัน
    - /api/account/get/profit/month/{month} หากำไรรายเดือน
    - /api/account/get/profit/year/{year} หากำไรรายปี
    - /api/income/get เรียกดูรายได้
    - /api/income/get/{id} เรียกดูรายได้ตาม id
    - /api/income/create เพิ่มรายได้
    - /api/income/get/allincome เรียกดูรายได้ทั้งหมด
    - /api/income/get/dateincome/{date} เรียกดูรายได้รายวัน
    - /api/income/get/monthincome/{month} เรียกดูรายได้รายเดือน
    - /api/income/get/yearincome/{year} เรียกดูรายได้รายปี
    - /api/income/get/list/date/{date} เรียกดูlistรายได้รายวัน
    - /api/income/get/list/month/{month} เรียกดูlistรายได้รายเดือน
    - /api/income/get/list/year/{year} เรียกดูlistรายได้รายปี
    - /api/income/get/mostincome/date/{date} เรียกดูรายได้รายวันที่สูงสุด
    - /api/income/get/mostincome/month/{month} เรียกดูรายได้รายเดือนที่สูงสุด
    - /api/income/get/mostincome/year/{year} เรียกดูรายได้รายปีที่สูงสุด
    - /api/outcome/get เรียกดูรายจ่าย
    - /api/outcome/get/{id} เรียกดูรายจ่ายตาม id
    - /api/outcome/create เพิ่มรายจ่าย คือ การซื้อของเพื่อเพิ่มของใน stock ดังนั้น service นี้จะเรียก stockservice ให้ updateจำนวนของ stock นั้นๆให้ด้วย
    - /api/outcome/get/alloutcome เรียกดูรายจ่ายทั้งหมด
    - /api/outcome/get/dateoutcome/{date} เรียกดูรายจ่ายรายวัน
    - /api/outcome/get/monthoutcome/{month} เรียกดูรายจ่ายรายเดือน
    - /api/outcome/get/yearoutcome/{year} เรียกดูรายจ่ายรายปี
    - /api/outcome/get/list/date/{date} เรียกดูlistรายจ่ายรายวัน
    - /api/outcome/get/list/month/{month} เรียกดูlistรายจ่ายรายเดือน
    - /api/outcome/get/list/year/{year} เรียกดูlistรายจ่ายรายปี
    - /api/outcome/get/mostoutcome/date/{date} เรียกดูรายจ่ายรายวันที่สูงสุด
    - /api/outcome/get/mostoutcome/year/{year} เรียกดูรายจ่ายรายเดือนที่สูงสุด
    - /api/outcome/get/mostoutcome/year/{year} เรียกดูรายจ่ายรายปีที่สูงสุด
```

    
    
    
## :wrench: Dev Tools
![Spring-boot](https://i.imgur.com/WF92VTP.png?1)<br />
![Imgur](https://i.imgur.com/YOqoyng.png?1)
## :family: Our Team
* พิทวัส ชูเชื้อ 60070064
* วาริน สุคันธเมศ 60070089
* ศุภมิตร บัวศรีแก้ว 60070100
* อิงครัต  ทินกรศรีสุภาพ 60070119
