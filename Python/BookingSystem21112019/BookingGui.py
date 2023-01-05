import tkinter as tk
from tkinter import *
from tkinter import messagebox
import BookingSystem

bdb = BookingSystem.Booking_Database()

class GUI:
    def __init__(self, master):

        self._master = master

        self._main_frame = tk.Frame(self._master)

        self._control_frame = tk.Frame(self._master)

        self._desc_entry1 = tk.Entry(self._control_frame)
        self._desc_entry2 = tk.Entry(self._control_frame)
        self._desc_entry3 = tk.Entry(self._control_frame)
        self._desc_entry4 = tk.Entry(self._control_frame)
        self._desc_entry5 = tk.Entry(self._control_frame)
        self._desc_entry6 = tk.Entry(self._control_frame)
        self._desc_entry7 = tk.Entry(self._control_frame)
        self._desc_entry8 = tk.Entry(self._control_frame)

        self._listbox = tk.Listbox(self._master)

        self._main_frame = tk.Frame(self._master)

        self.buttons = []

        self.slet_btn = tk.Button(self._control_frame, text="Slet booking", command = self.delete_booking)
        self._main_frame.grid()
        self.slet_btn.grid(row=16, column=2)
        self._control_frame.grid()

        self.opret_btn = tk.Button(self._control_frame, text = "Opret booking", command = self.add_booking)
        self._main_frame.grid()
        self.opret_btn.grid(row=5, column=2)
        self._control_frame.grid()

        self.ret_btn = tk.Button(self._control_frame, text="Ret booking", command = self.change_booking)
        self._main_frame.grid()
        self.ret_btn.grid(row=8, column=2)
        self._control_frame.grid()

        self.tidsinterval_btn = tk.Button(self._control_frame, text="Få bookings i tidsinterval", command = self.show_specific_list_of_bookings)
        self._main_frame.grid()
        self.tidsinterval_btn.grid(row=12, column=2)
        self._control_frame.grid()

        tk.Label(self._control_frame, text="Navn").grid(row=1, column=1)
        tk.Label(self._control_frame, text="Bilmærke").grid(row=2, column=1)
        tk.Label(self._control_frame, text="Tidspunkt").grid(row=3, column=1)
        tk.Label(self._control_frame, text="Dato").grid(row=4, column=1)
        tk.Label(self._control_frame, text="Fra").grid(row=10, column=1)
        tk.Label(self._control_frame, text="Til").grid(row=11, column=1)
        tk.Label(self._control_frame, text="ID").grid(row=15, column=1)
        tk.Label(self._control_frame, text="Fornavn Efternavn").grid(row=1, column=3, sticky=W)
        tk.Label(self._control_frame, text="Bilmærke").grid(row=2, column=3, sticky=W)
        tk.Label(self._control_frame, text="HH:MM").grid(row=3, column=3, sticky=W)
        tk.Label(self._control_frame, text="YYYYMMDD").grid(row=4, column=3, sticky=W)
        tk.Label(self._control_frame, text="ID").grid(row=7, column=1)
        tk.Label(self._control_frame, text="").grid(row=13, column=2)
        tk.Label(self._control_frame, text="").grid(row=6, column=2)
        tk.Label(self._control_frame, text="").grid(row=9, column=2)

        self._desc_entry1.grid(row=1, column=2)
        self._desc_entry2.grid(row=2, column=2)
        self._desc_entry3.grid(row=3, column=2)
        self._desc_entry4.grid(row=4, column=2)
        self._desc_entry8.grid(row=7, column=2)
        self._desc_entry5.grid(row=10, column=2)
        self._desc_entry6.grid(row=11, column=2)
        self._desc_entry7.grid(row=15, column=2)

        self._main_frame.grid()
        self._listbox.grid(row=1, column=4, columnspan=1, rowspan=1, sticky=N)
        self._control_frame.grid()

    
    def add_booking(self):
        if self._desc_entry1.get() == '' or self._desc_entry2.get() == '' or self._desc_entry3.get() == '' or self._desc_entry4.get() == '':
            tk.messagebox.showerror("Alle felter ikke udfyldt", "Udfyld alle felter")
        else:
            if bdb.check_booking(self._desc_entry3.get(), self._desc_entry4.get()) == 'occupied':
                tk.messagebox.showerror("Tidspunkt allerede optaget", "Vælg et andet tidspunkt")
            else:
                bdb.new_booking(self._desc_entry1.get(), self._desc_entry2.get(), self._desc_entry3.get(), int(self._desc_entry4.get()))
                self._listbox.delete(0, END)
                for i in bdb.list_bookings():
                    self._listbox.insert(END, i)

    def delete_booking(self):
        if self._desc_entry7.get() == '' or self._desc_entry7.get != int:
            tk.messagebox.showerror("Fejl", "Fejl")
        else:
            bdb.delete_booking(int(self._desc_entry7.get()))
            self._listbox.delete(0, END)
            for i in bdb.list_bookings():
                self._listbox.insert(END, i)

    def change_booking(self):
        if self._desc_entry8.get() == '' or self._desc_entry8.get != int:
            tk.messagebox.showerror("Fejl", "Fejl")
        else:
            if bdb.change_booking(int(self._desc_entry8.get()),self._desc_entry1.get(), self._desc_entry2.get(), self._desc_entry3.get(), int(self._desc_entry4.get())) == 'fail':
                tk.messagebox.showerror("Fejl", "Fejl")
            else:
                bdb.change_booking(int(self._desc_entry8.get()),self._desc_entry1.get(), self._desc_entry2.get(), self._desc_entry3.get(), int(self._desc_entry4.get()))
                self._listbox.delete(0, END)
                for i in bdb.list_bookings():
                    self._listbox.insert(END, i)

    def show_specific_list_of_bookings(self):
        if self._desc_entry5.get() == '' or self._desc_entry6.get() == '' or self._desc_entry5.get != int or self._desc_entry6.get != int:
            tk.messagebox.showerror("Fejl", "Fejl")
        else:
            tk.messagebox.showinfo("Tider i tidsintervallet", bdb.specific_list_of_bookings(int(self._desc_entry5.get()), int(self._desc_entry6.get())))
        


if __name__ == "__main__":

    window = tk.Tk()
    app = GUI(window)
    window.mainloop()
