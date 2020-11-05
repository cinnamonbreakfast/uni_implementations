bits 32

global start

extern exit, fprintf, fclose, fopen
import exit msvcrt.dll
import fprintf msvcrt.dll
import fclose msvcrt.dll
import fopen msvcrt.dll

segment data use32 class=data
    nume db "neculai.txt", 0
    mod_acces db "w", 0
    file_descr dd 0
    text db "Ce fac %d neculai", 0
    
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
        
        ; write
        push text
        push dword [file_descr]
        call [fprintf]
        
        ; close
        push dword 17
        push dword text
        push dword [file_descr]
        call [fclose]
        add esp, 4*3
        
        final:
    push dword 0
    call [exit]