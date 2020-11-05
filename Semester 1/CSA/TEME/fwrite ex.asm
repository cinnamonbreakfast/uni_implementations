bits 32

global start

extern exit, fread, fclose, fopen, printf
import exit msvcrt.dll
import fread msvcrt.dll
import fclose msvcrt.dll
import fopen msvcrt.dll
import printf msvcrt.dll

segment data use32 class=data
    nume db "neculai.txt", 0
    mod_acces db "r", 0
    file_descr dd 0
    len EQU 100
    sir times len db 0
    
    text db "Text: %s", 0
    
segment code use32 class=code:
    start:
        ; open
        push mod_acces
        push nume
        call [fopen]
        
        add esp, 4*2
        
        ; if open successfully
        cmp eax, 0
        je final
        
        mov [file_descr], eax
        
        ; write MOODLE.CS.UBBCLUJ.RO MAIL SCS
        push dword [file_descr]
        push dword len
        push dword 1
        push sir
        call [fread]
        add esp, 4*4
        
        ; eax = nr de caractere citite
        
        push sir
        call [printf]
        add esp, 4
        
        ; close
        push dword [file_descr]
        call [fclose]
        add esp, 4
        
        final:
    push dword 0
    call [exit]