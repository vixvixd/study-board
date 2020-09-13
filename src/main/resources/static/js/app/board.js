
  var board = {
    init: function() {
      var _this = this;
      const createBtn = document.querySelector('#submit');
      createBtn.addEventListener('click', _this.create);
    },
    create: function() {
      var data = {
        title: document.querySelector('#title').value,
        content: document.querySelector('#content').value,
        author: document.querySelector('#author').value,
      };
      fetch('/api/board', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
          'Content-Type': 'application/json'
        }
      }).then(function(response) { // 응답 처리!
        if (response.ok) {
          alert('게시글이 작성 되었습니다.');
          window.location.href='/';
        } else {
          alert('게시글 작성에 문제가 생겼습니다.');
        }
      });
    }
  };
  board.init();
