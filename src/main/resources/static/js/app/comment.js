var comment = {
  init: function() {
    var _this = this;

    const createBtn = document.querySelector('#comment-create-btn');

    createBtn.addEventListener('click', function(){
      _this.create();
    });

 // 버튼 토글 기능 추가
    const editBtn = document.querySelectorAll('.comment-edit-btn');

    editBtn.forEach(function(item) {
      item.addEventListener('click', function() {
        if (item.innerHTML == '수정') {
          item.innerHTML = '취소';
        } else {
          item.innerHTML = '수정';
        }
      });
    });


    const updateBtn = document.querySelectorAll('.comment-update-btn');

    updateBtn.forEach(function(item) {
      item.addEventListener('click', function() {
        var form = this.closest('form');
        _this.update(form);
      });
    });
  },

  create: function() {
    var data = {
      author: document.querySelector('#comment-author').value,
      content: document.querySelector('#comment-content').value,
    };

    var split = location.pathname.split('/');
    var boardId = split[split.length - 1];

    fetch('/api/comments/' + boardId, {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(function(response) {
      if (response.ok) {
        alert('댓글이 등록되었습니다.');
        window.location.reload(`/${boardId}#comment`);
      } else {
        alert('댓글 등록 실패..!');
      }
    });
  },

  update: function(form) {
    var data = {
      id: form.querySelector('#comment-id').value,
      author: form.querySelector('#comment-author').value,
      content: form.querySelector('#comment-content').value,
    };

    var split = location.pathname.split('/');
    var boardId = split[split.length - 1];

    fetch('/api/comments/' + data.id, {
      method: 'PUT',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(function(response) {
      if (response.ok) {
        alert('댓글이 수정되었습니다.');
      } else {
        alert('댓글 수정 실패..!');
      }
      window.location.reload(true);
    });
  }
};
comment.init();
