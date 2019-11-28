# SopPos
Microservice ระบบ Pos (Point of Sale)
![Imgur](https://www.mx7.com/i/0bc/MN3Dwf.jpg)
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
* Checkout
  * เป็นฟังก์ชันสรุปใบเสร็จ เก็บรายรับ-รายจ่าย เข้า Account
* Accounting
  * สรุปข้อมูลการซื้อวัตถุดิบ(Stock)
  * สรุปยอดขายสินค้า รายวัน/เดือน/ปี
  
## Route
  * Stock port 8096
    **/api/stock เรียกดู stock
    **/api/stock/add เพิ่ม stock
    **/api/stock?name=xx เรียกดู ลบ อัพเดท จากขื่อ
  * Product port 8093
    **/api/products เรียกดู product
    **/api/products/add เพิ่ม product
    */api/products/{id} เรียกดู product ตาม id
    */api/products?name=xx เรียกดู ลบ อัพเดท จากขื่อ
  * Productlist port 8094
    */api/productlist เรียกดู productlist
    */api/product/add เพิ่ม productlist
    */api/product/{id} เรียกดู product ตาม id
    */api/product?name=xx เรียกดู ลบ อัพเดท จากขื่อ
    *api/product?skuid=xx อัพเดทจาก skuid
  * Order port 8099
    */api/order/create สร้าง order
    */api/order/get เรียกดู order
    */api/order/get/date/{date} เรียกดูตามวัน
    */api/order/get/month/{month} เรียกดูตามเดือน
    */api/order/get/year/{year} เรียกดูตามปี
    */api/order/get/update อัพเดท
  * OrderList port 8102
    */api/orderlist/create สร้าง orderlist
    */api/orderlist/get เรียกดู orderlist
    */api/orderlist/get/{id} เรียกดู orderlist ตาม id
    */api/orderlist/create สร้าง orderlist
    */api/orderlist/get/name/{name} เรียกดู orderlist ตามชื่อ
    */api/orderlist/get/oid/{oid} เรียกดู orderlist ตาม oid
    */api/orderlist/update อัพเดท
    */api/orderlist/delete/{id} ลบตาม id
    */api/orderlist/delete/oid/{oid} ลบตาม oid
  * Invoice port 8095
    */api/invoice/{id} เรียกดู invoice ตาม id
    */api/invoice/create สร้าง invoice
  * account port 8097
    */api/account/get/profit/all หากำไรทั้งหมด
    */api/account/get/profit/date/{date} หากำไรรายวัน
    */api/account/get/profit/month/{month หากำไรรายเดือน
    */api/account/get/profit/year/{year} หากำไรรายปี
    */api/income/get เรียกดูรายได้
    */api/income/get/{id} เรียกดูรายได้ตาม id
    */api/income/create เพิ่มรายได้
    */api/income/getallincome เรียกดูรายได้ทั้งหมด
    */api/income/get/dateincome/{date} เรียกดูรายได้รายวัน
    */api/income/get/monthincome/{month} เรียกดูรายได้รายเดือน
    */api/income/get/yearincome/{year} เรียกดูรายได้รายปี
    */api/income/get/list/date/{date} เรียกดูlistรายได้รายวัน
    */api/income/get/list/month/{month} เรียกดูlistรายได้รายเดือน
    */api/income/get/list/year/{year} เรียกดูlistรายได้รายปี
    */api/income/get/mostincome/date/{date} เรียกดูรายได้รายวันที่สูงสุด
    */api/income/get/mostincome/year/{year} เรียกดูรายได้รายเดือนที่สูงสุด
    */api/income/get/mostincome/year/{year} เรียกดูรายได้รายปีที่สูงสุด
     */api/income/get เรียกดูรายได้
    */api/income/get/{id} เรียกดูรายได้ตาม id
    */api/income/create เพิ่มรายได้
    */api/income/getallincome เรียกดูรายได้ทั้งหมด
    */api/income/get/dateincome/{date} เรียกดูรายได้รายวัน
    */api/income/get/monthincome/{month} เรียกดูรายได้รายเดือน
    */api/income/get/yearincome/{year} เรียกดูรายได้รายปี
    */api/income/get/list/date/{date} เรียกดูlistรายได้รายวัน
    */api/income/get/list/month/{month} เรียกดูlistรายได้รายเดือน
    */api/income/get/list/year/{year} เรียกดูlistรายได้รายปี
    */api/income/get/mostincome/date/{date} เรียกดูรายได้รายวันที่สูงสุด
    */api/income/get/mostincome/year/{year} เรียกดูรายได้รายเดือนที่สูงสุด
    */api/income/get/mostincome/year/{year} เรียกดูรายได้รายปีที่สูงสุด
    */api/outcome/get เรียกดูรายจ่าย
    */api/outcome/get/{id} เรียกดูรายจ่ายตาม id
    */api/outcome/create เพิ่มรายจ่าย
    */api/outcome/getalloutcome เรียกดูรายจ่ายทั้งหมด
    */api/outcome/get/dateoutcome/{date} เรียกดูรายจ่ายรายวัน
    */api/outcome/get/monthoutcome/{month} เรียกดูรายจ่ายรายเดือน
    */api/outcome/get/yearoutcome/{year} เรียกดูรายจ่ายรายปี
    */api/outcome/get/list/date/{date} เรียกดูlistรายจ่ายรายวัน
    */api/outcome/get/list/month/{month} เรียกดูlistรายจ่ายรายเดือน
    */api/outcome/get/list/year/{year} เรียกดูlistรายจ่ายรายปี
    */api/outcome/get/mostoutcome/date/{date} เรียกดูรายจ่ายรายวันที่สูงสุด
    */api/outcome/get/mostoutcome/year/{year} เรียกดูรายจ่ายรายเดือนที่สูงสุด
    */api/outcome/get/mostoutcome/year/{year} เรียกดูรายจ่ายรายปีที่สูงสุด


    
    
    
## :wrench: Dev Tools
![Spring-boot](https://i.imgur.com/WF92VTP.png?1)<br />
![Imgur](https://i.imgur.com/YOqoyng.png?1)
## :family: Our Team
* พิทวัส ชูเชื้อ 60070064
* วาริน สุคันธเมศ 60070089
* ศุภมิตร บัวศรีแก้ว 60070100
* อิงครัต  ทินกรศรีสุภาพ 60070119
