## build stage##
FROM python:3.10-slim-buster as build

WORKDIR /app

RUN pip install requests beautifulsoup4
CMD ["python", "./test.py"]

