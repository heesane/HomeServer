from database import Base
from sqlalchemy.sql import func
from sqlalchemy import (
    Column,
    Integer,
    String,
    DateTime,
    Boolean
)

## Local S3 Model
## 파일형식은 사진, 영상, 파일로 구분

class Pictures(Base):
    __tablename__ = 'pictures'
    
    picture_id = Column(Integer,primary_key=True) ## Picture ID
    picture_name = Column(String(100),nullable=False) ## Picture Name
    picture_size = Column(Integer,nullable=False) ## Picture Size
    picture_format = Column(String(10),nullable=False,default='jpg') ## Picture Format
    picture_created_at = Column(DateTime(timezone=True),nullable=False,default=func.now()) ## Picture Created At
    picture_updated_at = Column(DateTime(timezone=True),nullable=False,default=func.now(),onupdate=func.now()) ## Picture Updated At
    picture_deleted_at = Column(DateTime(timezone=True),nullable=True) ## Picture Deleted At
    
class Videos(Base):
    __tablename__ = 'videos'
    videos_id = Column(Integer,primary_key=True) ## Video ID
    videos_name = Column(String(100),nullable=False) ## Video Name
    videos_size = Column(Integer,nullable=False) ## Video Size
    videos_running_time_hour = Column(Integer,nullable=False) ## Video Running Time Hour
    videos_running_time_minute = Column(Integer,nullable=False) ## Video Running Time Minute
    videos_running_time_second = Column(Integer,nullable=False) ## Video Running Time Second
    videos_created_at = Column(DateTime(timezone=True),nullable=False,default=func.now()) ## Video Created At
    videos_updated_at = Column(DateTime(timezone=True),nullable=False,default=func.now(),onupdate=func.now()) ## Video Updated At
    videos_deleted = Column(Boolean,nullable=False,default=False) ## Video Deleted
    
class Files(Base):
    __tablename__ = 'files'
    file_id = Column(Integer,primary_key=True) ## File ID
    file_name = Column(String(100),nullable=False) ## File Name
    file_size = Column(Integer,nullable=False) ## File Size
    file_format = Column(String(10),nullable=False,default='txt') ## File Format
    file_created_at = Column(DateTime(timezone=True),nullable=False,default=func.now()) ## File Created At
    file_updated_at = Column(DateTime(timezone=True),nullable=False,default=func.now(),onupdate=func.now()) ## File Updated At
    file_deleted_at = Column(DateTime(timezone=True),nullable=True) ## File Deleted At