bits 32

global start

extern exit, scanf, printf
import exit msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll

segment data use32 class=data
    num_format db "%d", 0
    fnal db "LOL %d", 10, 0
    str_format db "%s", 0
    
    i db 10
    
    len1 equ 120
    ln dd 0
    
    sir times len1 dd 0
    sir2 times len1 db 0
    
segment code use32 class=code:
    start:
        push ln
        push num_format
        call [scanf]
        add esp, 4*2
        
        mov esi, 0
        mov ecx, [ln]
        
        read:
        
            lea eax, [sir+esi]
            add esi, 4
            
            pushad
            
            push eax
            push num_format
            call [scanf]
            add esp, 4*2
            
            popad
            
        loop read
        
        mov esi, 0
        mov ecx, [ln]
        
        mov esi, 0
        mov ecx, [ln]
        
        read2:
        
            lea eax, [sir+esi]
            add esi, 4
            
            pushad
            
            push dword [eax]
            push fnal
            call [printf]
            add esp, 4*2
            
            popad
            
        loop read2
        
    push dword 0
    call [exit]
    