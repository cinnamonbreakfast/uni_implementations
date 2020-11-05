bits 32

global _asmRot

segment data public data use32

segment code public code use32
_asmRot:

    push ebp
    mov ebp, esp
        
    mov eax, [ebp+8]
    ror eax, 4
    ;add eax, [ebp+12]
        
    pop ebp
    RET 4
