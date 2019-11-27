bits 32

global start

extern exit, printf, scanf, fopen, fprintf, fclose

import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll

import fopen msvcrt.dll
import fprintf msvcrt.dll
import fclose msvcrt.dll

; An array of dwords is defined in the data segment
; Display each element


; Se citeste numele unui fisier si un mesaj
; Mesajul este apoi scris in fisier

segment data use32 class=data
    mesaj1 db "Nume fisier: ",0
    mesaj2 db "Text: ", 0
    
    space db 10, 0

    len1 equ 30
    len2 equ 120
    fname times len1 db 0
    ftext times len2 db 0
    format db "%s", 0
    format2 db "%x", 0
    
    writef db "%s", 32, 0
    
    access_mode db "w+", 0
    am2 db "r", 0
    file_descr dd 0
    read dd 0
    
segment code use32 class=code:
    start:
        ; FILE NAME BEGIN
            ; afisare mesaj
        push mesaj1
        push format
        call [printf]
        add esp, 4*2
    
            ; citire nume fisier
        push fname
        push format
        call [scanf]
        add esp, 4*3 ; 
        
        ; FILE NAME END
        
        ; FILE MESSAGE BEGIN
            ; afisare mesaj
        push mesaj2
        push format
        call [printf]
        add esp, 4*2
            
            ; citire mesaj
        push ftext
        push format
        call [scanf]
        add esp, 4*3
        
        ; FILE MESSAGE END
        
        ; FILE HANDLING BEGIN
        ; We open the file
        push access_mode
        push fname
        call [fopen]
        
        add esp, 4*2 ; golire stiva
        
        mov [file_descr], eax ; salvam 'descriptorul' fisierului
        
        ; scrierea mesajului
        push dword ftext
        push dword writef
        push dword [file_descr]
        call [fprintf]
        add esp, 4*3
        
        ; inchiderea fisierului
        push dword [file_descr]
        call [fclose]
        add esp, 4
        
    push dword 0
    call [exit]