FROM node:12-alpine as builder
WORKDIR /app
COPY . /app
RUN npm install && npm run build --prod

FROM nginx:alpine

ADD nginx-default.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /app/dist/covid-19-dashboard/ /var/www/frontend/

RUN echo "mainFileName=\"\$(ls /var/www/frontend/main*.js)\" && \
          envsubst '\$BACKEND_API_URL \$BACKEND_NOTIFICATION_URL \$BACKEND_NOTIFICATION_PORT ' < \${mainFileName} > main.tmp && \
          mv main.tmp  \${mainFileName} && nginx -g 'daemon off;'" > run.sh

ENTRYPOINT ["sh", "run.sh"]
