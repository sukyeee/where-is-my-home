export default {
    getCodeName(group_code, code) {
        switch (group_code) {
            case "001": {
                switch (code) {
                    case "001": return "관리자";
                    case "002": return "일반회원";
                }
            } case "002": {
                switch (code) {
                    case "001": return "공지사항";
                    case "002": return "업데이트";
                }
            } case "003": {
                switch (code) {
                    case "001": return "시작 전";
                    case "002": return "진행 중";
                    case "003": return "종료";
                }
            } case "004": {
                switch (code) {
                    case "001": return "미참가";
                    case "002": return "참가";
                }
            }
        }
        return -1;
    },
    makeDateStr(year, month, day, type){ // type + / . 2022/07/24 2022.11.24
        return year + type + ( ( month < 10 ) ? '0' + month : month ) + type + ( ( day < 10 ) ? '0' + day : day );
    },
    makeTimeStr(hour, minute, second, type){ // type + / . 2022/07/24 2022.11.24
        return hour + type + ( ( minute < 60 ) ? '0' + minute : minute ) + type + ( ( second < 60 ) ? '0' + second : second );
    },
    // 정수 난수 생성
    getRandomInt(min, max) {
        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(Math.random() * (max - min)) + min; //최댓값은 제외, 최솟값은 포함
      }

}