from fastapi import FastAPI,Request
from starlette.middleware.cors import CORSMiddleware
from datetime import datetime

from routes.files import files_router
from routes.videos import videos_router
from routes.pictures import pictures_router

app = FastAPI(
    title="S3 Service API Docs",
    description="Simple S3 Service with FastAPI",
    version="Beta Version"
)

origins = [
    "http://localhost:3000",
    "http://localhost:8000",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.middleware("http")
async def log_requests(request: Request, call_next):
    # 요청 정보 추출
    method = request.method
    url = request.url
    client_ip = request.client.host
    
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    # 로그 메시지 생성
    log_message = f"[{timestamp}] IP: {client_ip} - Method: {method} - URL: {url}\n"

    # 로그 파일에 기록
    with open("server_log.txt", "a") as log_file:
        log_file.write(log_message)

    response = await call_next(request)
    return response

app.include_router(files_router.router)
app.include_router(videos_router.router)
app.include_router(pictures_router.router)