
  var board = {
    init: function() {
      var _this = this;
      const createBtn = document.querySelector('#submit');
      const deleteBtn = document.querySelector('#delete');
      const updateBtn = document.querySelector('#update');


     if (createBtn) {
           createBtn.addEventListener('click', _this.create);
         }
     if (deleteBtn) {
           deleteBtn.addEventListener('click', _this.delete);
         }
     if (updateBtn) {
           updateBtn.addEventListener('click', _this.update);
         }
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
      }).then(function(response) {
        if (response.ok) {
          alert('게시글이 작성 되었습니다.');
          window.location.href='/';
        } else {
          alert('게시글 작성에 문제가 생겼습니다.');
        }
      });
    },
    update: function() {
      var data = {
        id: document.querySelector('#board_id').value,
        title: document.querySelector('#title').value,
        content: document.querySelector('#content').value,
        author: document.querySelector('#author').value,
          };
          fetch('/api/board/'+data.id , {
            method: 'PUT',
            body: JSON.stringify(data),
            headers: {
              'Content-Type': 'application/json'
            }
          }).then(function(response) {
            if (response.ok) {
              alert('게시글이 작성 되었습니다.');
              window.location.href='/'+data.id;
            } else {
              alert('게시글 작성에 문제가 생겼습니다.');
            }
          });
        },
      delete: function() {
        var split = location.pathname.split('/');
        var id = split[split.length - 1];
        fetch('/api/board/' + id, {
          method: 'DELETE',
        }).then(function(response) {
          if (response.ok) {
            alert('글이 삭제 되었습니다.');
            window.location.href='/';
          } else {
            alert('글을 삭제할 수 없습니다.');
          }
        });
      }
  };
  board.init();
