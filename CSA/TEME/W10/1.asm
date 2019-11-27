bits 32

global start

extern exit, printf, scanf
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll


; Se citeste un numar de la tastatura
; Se afiseaza scrierea numarului in baza 16

segment data use32 class=data
    Z dd 0
    H dd 0
    
    format db "%d",0
    msj db "%d = 0x%x",0
    
segment code use32 class=code:
    start:
        
        ; citirea numarului
        push Z
        push format
        call [scanf]
        add esp, 4*2 ; golire stiva
        
        mov ECX, [Z]
        mov [H], ECX
        
        ; afisarea sa
        push dword [H]
        push dword [Z]
        push msj
        call [printf]
        
        add esp, 4*3 ; golire stiva
        
        
    push dword 0
    call [exit]