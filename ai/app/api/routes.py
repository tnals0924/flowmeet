from fastapi import APIRouter, UploadFile, File
from app.services.sub_summary import make_sub_summary, make_mermaid_code

router = APIRouter()

@router.post("/sub-summary")
async def sub_summary(file: UploadFile = File(...)):
    text = await file.read()
    text = text.decode("utf-8") 

    summary = make_sub_summary(text)
    mermaid_code = make_mermaid_code(text)
    
    mermaid_code = mermaid_code.replace("```mermaid", "").replace("```", "").strip()
    
    return {
        "summary": summary,
        "mermaid_code": mermaid_code
    }