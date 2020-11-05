bits 32

global start

extern exit
import exit msvcrt.dll

extern suma


; %include "sum.asm"

segment data use32 class=data
    
    
segment code use32 class=code:    
    start:
    
        push dword 2
        push dword 3
        call suma
        
        ;nasm -f obj simple_func.asm
        ;nasm -f obj sum.asm
        ;alink simple_func.obj sum.obj -oPE -subsys console -entry start
        
    
    push dword 0
    call [exit]
    