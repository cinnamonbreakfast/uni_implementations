bits 32

global start

extern exit, printf, scanf
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll

extern permuta


; %include "sum.asm"

segment data use32 class=data
    nr dd 0
    dform db '%d', 0
    xform db '%x', 32, 0
    
    mesaj db 'Numar:', 0
    
    
segment code use32 class=code:    
    start:
        
        push mesaj
        call [printf]
        add esp, 4
        
        push dword nr
        push dword dform
        call [scanf]
        add esp, 4*2
        
        push dword [nr]
        push dword xform
        call [printf]
        add esp, 4*2
    
        mov ecx, 7
    
        bucluc:
            push ecx
            
            push dword [nr]
            call permuta
            
            mov [nr], eax
            
            push dword eax
            push dword xform
            call [printf]
            add esp, 4*2
            
            mov eax, dword [nr]
            
            pop ecx
            
        loop bucluc
        
        ;nasm -f obj main.asm
        ;nasm -f obj second.asm
        ;alink main.obj second.obj -oPE -subsys console -entry start
        
    
    push dword 0
    call [exit]
    