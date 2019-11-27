bits 32 ; assembling for the 32 bits architecture

global start        


extern exit
import exit msvcrt.dll 


segment data use32 class=data
    a db '1234567890'
    len_a EQU ($-a)
    b db 'a1b3c7'
    len_b EQU ($-b)


segment code use32 class=code
    start:
        MOV EDX, 0 ; EDX = count
        MOV ESI, a
        MOV EDI, b
        
        CLD
        
        MOV ECX, len_a
        
        bucla_for_1:
            LODSB ; AL = a[i]
            
            MOV EDI, b
            push ecx
            MOV ECX, len_b
            bucla_for_2:
                SCASB ; cmp al, b[j]
                JE found
            LOOP bucla_for_2
            jmp mai_departe
            found:
                INC EDX ; count++
                
            mai_departe:
            pop ecx
        LOOP bucla_for_1
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
