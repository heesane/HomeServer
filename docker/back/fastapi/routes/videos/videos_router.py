from fastapi import APIRouter, File, UploadFile

router = APIRouter(
    prefix="/videos",
    tags=["videos"]
)

@router.get("/")
async def main_videos():
    return {"message": "videos"}