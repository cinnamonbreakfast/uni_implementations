bits 32

global start
extern fnc

extern exit, printf

import exit msvcrt.dll
import printf msvcrt.dll

segment data use32 class=data public
    sir dd 1234A678h, 12345678h, 1AC3B47Dh, 0FEDC9876h
    ln1 equ ($-sir)/4
    elem_size equ 4
    
    sir2 times ln1 db 0
    
    format db "%d", 0
    
    suma dw 0
    ind db 0
    
    
segment code use32 class=code public
    start:
        
        
        mov esi, sir
        mov edi, sir2
        cld
        
        mov ebx, 0
        
        mov ecx, ln1
        
        cat_timp:
            push ecx ; save ecx for first loop
            
            push sir
            push ind
            push suma
            call fnc
            add esp, 4*2
            
            pop ecx
            
        loop cat_timp
        
        mov ecx, 4
        mov eax, 0
        mov esi, sir2
        
        afisare:
        
        push ecx
        
        push dword eax
        push dword format
        call [printf]
        add esp, 4*2
        
        pop ecx
        
        lodsb
        
        loop afisare
        
    
    push dword 0
    call [exit]