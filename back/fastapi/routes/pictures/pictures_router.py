from fastapi import APIRouter, File, UploadFile

router = APIRouter(
    prefix="/pictures",
    tags=["pictures"]
)

@router.get("/")
async def main_pictures():
    return {"message": "pictures"}