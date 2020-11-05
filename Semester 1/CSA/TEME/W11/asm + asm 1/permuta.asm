; protect from multiple inclusions
; %ifndef _SUMA_ASM_
; %define _SUMA_ASM_

bits 32
segment code use32 public code
global permuta

    permuta:
        push ebp
        mov ebp, esp
        
        mov eax, [ebp+8]
        ror eax, 4
        ;add eax, [ebp+12]
        
        pop ebp
        RET 4
        
        
; %endif
