bits 32

global start

extern exit, scanf, printf, fscanf
import exit msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll

segment data use32 class=data
    num_format db "%d", 0
    fnal db "LOL %d", 10, 0
    str_format db "%s", 0
    nume
    
    i db 10
    
    len1 equ 120
    ln dd 0
    
    sir times len1 dd 0
    sir2 times len1 db 0
    
segment code use32 class=code:
    start:
        
        
    push dword 0
    call [exit]
    