import tkinter as tk
import vk

window = tk.Tk()
window.title("My Window")
window.geometry("300x300")

label1 = tk.Label(window, text="id = ")
label2 = tk.Label(window, text="Имя = ")   
label3 = tk.Label(window, text="Фамилия = ")
label4 = tk.Label(window, text="Пол = ")


userFields = ["id","first_name","last_name","sex","deactivated","is_closed","bdate", "career", "followers_count",  "last_seen", "online", "relation"]
access_t='vk1.a.TCeOR7BHocnbRuk8IEpbJ_uxy-4bY1iub5oQiXbaBOZGp3XkRFyQr-H5CHZQ_nUhB-g28mzFG6009YlnF_pzcziassEKP6JHwcAghXs5P5nFf7LIctKFJB8W_ShL-ZcCjKiTbTLpMVDfF4oUTxjibTisUrt3ocFCK_hD8ZPKZUeDD6QMemYvxCFCT3r-QqGpbnKle39S4paoMCc0iW84Mw'
vk_api=vk.API(access_token=access_t)
textbox1 = tk.Entry(window)




def getData():
    text = textbox1.get()
    userdata = vk_api.users.get(user_ids=[text],fields=userFields, v=5.131)
    print("text")
    print(text)
    print("text")
    label1.config(text=f"id = {userdata[0]['id']}")
    label2.config(text=f"Имя = {userdata[0]['first_name']}")
    label3.config(text=f"Фамилия = {userdata[0]['last_name']}")
    label4.config(text=f"Пол = {userdata[0]['sex']}")
   
button = tk.Button(window, text="Вытащить", command=getData)

textbox1.pack()
button.pack()
label1.pack()
label2.pack()   
label3.pack()
label4.pack()

window.mainloop()