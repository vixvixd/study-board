var comment = {
  init: function() {
    var _this = this;
    const createBtn = document.querySelector('#comment-create');
    createBtn.addEventListener('click', function(){
      _this.create();
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
        window.location.reload();
      } else {
        alert('댓글 등록 실패..!');
      }
    });
  }
};
comment.init();