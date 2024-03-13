from fastapi import APIRouter, File, UploadFile

router = APIRouter(
    prefix="/files",
    tags=["files"]
)

@router.get("/")
async def main_files():
    return {"message": "files"}