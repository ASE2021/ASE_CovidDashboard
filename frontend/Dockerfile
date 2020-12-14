FROM node:12-alpine as builder
WORKDIR /app
COPY . /app
RUN npm install && npm run build

FROM nginx:alpine

ADD nginx-default.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /app/dist/covid-19-dashboard/ /var/www/frontend/

RUN echo "nginx -g 'daemon off;'" > run.sh

ENTRYPOINT ["sh", "run.sh"]
