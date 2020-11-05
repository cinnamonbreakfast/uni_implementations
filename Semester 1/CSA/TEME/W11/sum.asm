; protect from multiple inclusions
; %ifndef _SUMA_ASM_
; %define _SUMA_ASM_

bits 32
segment code use32 public code
global suma

    suma:
        push ebp
        mov ebp, esp
        
        mov eax, [ebp+8]
        add eax, [ebp+12]
        
        pop ebp
        RET 8
        
; %endif